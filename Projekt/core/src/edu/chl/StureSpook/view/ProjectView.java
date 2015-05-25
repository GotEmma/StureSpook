package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import edu.chl.StureSpook.model.Enemy;
import edu.chl.StureSpook.model.GameModel;
import edu.chl.StureSpook.model.Player;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectView extends InputAdapter implements GameView,PropertyChangeListener{

    private GameModel model;
    private SpriteBatch batch,guiBatch;
    private ShapeRenderer shapeRenderer;
    private HashMap<String,Sprite> sprites;
    private OrthographicCamera camera;
    private ArrayList<DesktopInputListener> listeners;
    private TextureAtlas textureAtlas;
    private int screenMouseX, screenMouseY;
    private String currentLvlTextureName;
    private TextureAtlas currentLvlTextureAtlas;
    private Animation playerWalking;
    private Animation playerWalkingLeft;
    private TextureRegion[] animationKeyFrames;
    private float animationState = 0;
    private TextureRegion playerFrame;
    
    private GUIDrawable[] visibleGUIElements;
    private GUIClickable[] clickableGUIElements;
    
    private FrameBuffer lightMap;

    public ProjectView(GameModel model) {
        this.model = model;
        listeners = new ArrayList<DesktopInputListener>();
    }
    
    private void loadAssets() {
        
        textureAtlas = new TextureAtlas("packed/sharedTextures.pack");
        
        // Load first level
        currentLvlTextureName = model.getCurrentLevel().getMapTextureName();
        currentLvlTextureAtlas = new TextureAtlas("packed/testLevel.pack");
        
    }
    
    private void buildGUI() {
        GUIButton menuButton = new GUIButton("menu","menuButton","menuButtonMouseover",camera.viewportWidth-64,0,32,16);
        GUIVolumeControl volumeControl = new GUIVolumeControl(0.5f,camera.viewportWidth-32,0);
        this.clickableGUIElements = new GUIClickable[]{menuButton,volumeControl};
        this.visibleGUIElements = new GUIDrawable[]{menuButton,volumeControl};
        
    }
    
    private void doGUIAction(String command) {
        System.out.println("Command pressed: " + command);
    }
    
    @Override
    public void init() {
        this.loadAssets();
        batch = new SpriteBatch();
        guiBatch = new SpriteBatch();
       // animationKeyFrames = new TextureRegion [] {new TextureRegion(new Texture(""))}; 
        playerWalking = new Animation(4, textureAtlas.findRegions("playerWalk"));
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        
        buildGUI();
        
        lightMap = new FrameBuffer(Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
    }
    
    private void render(){
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Player player = this.model.getPlayer();
        
        Enemy enemy1 = model.getCurrentLevel().createEnemy("spider", 20, 30);
        Enemy enemy2 = model.getCurrentLevel().createEnemy("spikes", 200, 40);
        
        
        float cameraX = Math.max(player.getX(),camera.viewportWidth/2); //left limit
        cameraX = Math.min(cameraX, 
                this.model.getCurrentLevel().getWidth()-(camera.viewportWidth/2) );//right limit
        
        float cameraY = Math.max(player.getY(),camera.viewportHeight/2); //bottom limit
        cameraY = Math.min(cameraY, 
                this.model.getCurrentLevel().getHeight()-(camera.viewportHeight/2) );//top limit
        
        camera.position.set(cameraX, cameraY, 100);
        camera.update();
        
        // DRAWS BACKGROUND
	batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(currentLvlTextureAtlas.findRegion(this.model.getCurrentLevel().getBackgroundImageName()), 0, 0);
        
        
        int[][] tileMap = model.getCurrentLevel().getTileMap();
        for(int i = tileMap.length-1; i >= 0; i--){
            for(int j = tileMap[i].length-1; j >= 0; j--){
                if(tileMap[i][j] != -1){
                    batch.draw(currentLvlTextureAtlas.findRegion(tileMap[i][j]+""),i*16,j*16);
                }
            }
        }
        
        // DRAWS PLAYER + Other Objects
        if(player.getMoveRight() || player.getMoveLeft()){
            playerFrame = playerWalking.getKeyFrame(this.animationState);
            playerFrame.flip((player.getMoveLeft() ^ playerFrame.isFlipX()), false);
            
            batch.draw(playerFrame, player.getX(), player.getY());
            playerWalking.setPlayMode(Animation.PlayMode.LOOP);
            this.animationState +=1;
        } 
        else if(player.isJumping()) {
            batch.draw(textureAtlas.findRegion("playerJump"), player.getX(), player.getY());
        }
        else {
            batch.draw(textureAtlas.findRegion(player.getTextureNameStandStill()),player.getX() ,player.getY());
        }
        
        //batch.draw(textureAtlas.findRegion(player.getTextureName()),player.getX() ,player.getY());
        //batch.draw(textureAtlas.findRegion(player.getTextureName()),enemy1.getX(), enemy1.getY());
        //batch.draw(textureAtlas.findRegion(player.getTextureName()),enemy2.getX(), enemy2.getY());
        batch.end();
        /*
=======
        
        // DRAWS TILEMAP
        if(currentLvlTextureName != model.getCurrentLevel().getMapTextureName()){
            currentLvlTextureName = model.getCurrentLevel().getMapTextureName();
            currentLvlTextureAtlas = new TextureAtlas("packed/" +currentLvlTextureName);
        }
        
>>>>>>> master
        //DRAW FLASHLIGHT HERE
        float[] polygon  = this.model.getFlashlightPolygon(); //Gör något med denna
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.line(polygon[0], polygon[1], polygon[2], polygon[3], Color.MAGENTA, Color.CYAN);//Rita helsvart över skärmen senare, med ett transparent hål som motsvarar ficklampsljus
        shapeRenderer.end();
        */
        this.drawLightFrameBuffer();
        
        //DRAW USER INTERFACE HERE
        guiBatch.begin();
        for (GUIDrawable b : visibleGUIElements) {
            b.draw(guiBatch, textureAtlas, screenMouseX, screenMouseY);
        }
        guiBatch.end();
    }
    
    public void addInputListener(DesktopInputListener listener){
        listeners.add(listener);
    }
    
    @Override
    public boolean keyUp(int i) { 
        for(DesktopInputListener l:listeners){
            l.keyUp(i);
        }
        return true;
    }
    
    @Override
    public boolean keyDown(int i) {
        for(DesktopInputListener l:listeners){
            l.keyDown(i);
        }
        return true;
    }
    
    @Override
    public boolean mouseMoved(int x, int y) { 
        screenMouseX = x;
        screenMouseY = (int)camera.viewportHeight - y;
        Vector3 coords = camera.unproject(new Vector3(x,y,0));
        for(DesktopInputListener l:listeners){
            l.mouseMoved((int)coords.x, (int)coords.y);
        }
        return true; 
    }
    
    @Override
    public boolean touchDown(int x, int y,int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            y = (int)camera.viewportHeight-y;
            for (GUIClickable b: clickableGUIElements) {
                String command = b.click(x, y);
                if (!command.equals("oob")) {
                    this.doGUIAction(command);
                }
            }
        }
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.render();
    }
    
    
    private void drawLightFrameBuffer() {
        shapeRenderer.begin(ShapeType.Filled);
        
        //start drawing to frame buffer
        lightMap.begin();
        
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.gl.glBlendFuncSeparate(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA, GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //draw outer circle to buffer with partial transparency
        shapeRenderer.setColor(0, 0, 0, 0.7f);
        shapeRenderer.circle(200, 200, 150);
        
        //draw fully transparent "hole" in outer circle
        shapeRenderer.setColor(0, 0, 1, 0);
        shapeRenderer.circle(200, 200, 120);
        
        //flush shapeRenderer buffer to frame buffer and stop drawing
        shapeRenderer.end();
        
        //stop drawing to frame buffer
        lightMap.end();
        
        //draw to GUI batch to make sure texture always covers screen
        guiBatch.begin();
        //fetch frame buffer as texture and draw to screen
        guiBatch.draw(lightMap.getColorBufferTexture(), 0, 0);
        guiBatch.end();
        
    }
    

}
