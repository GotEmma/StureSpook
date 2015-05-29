package edu.chl.StureSpook.view;

import edu.chl.StureSpook.controller.DesktopInputListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

import edu.chl.StureSpook.model.DeadlyObsticles;
import edu.chl.StureSpook.model.DrawableWorldObjects;

import edu.chl.StureSpook.Options;

import edu.chl.StureSpook.model.GameModel;
import edu.chl.StureSpook.model.Player;
import edu.chl.StureSpook.model.World;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectView extends InputAdapter implements GameView,PropertyChangeListener{

    private World model;
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
    private Options options;
    
    private GUIDrawable[] visibleGUIElements;
    private GUIClickable[] clickableGUIElements;
    
    private FrameBuffer lightMap;
    private Texture lightMapTexture;
    
    private Sound walking;
    private Sound running;
    
    

    public ProjectView(World model) {
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
        GUIInventory inventory = new GUIInventory(model.getInventory());
        GUIPlayerHealth playerHealth = new GUIPlayerHealth(model.getPlayer(), inventory.getWidth(), 0, 32, 160);
        this.clickableGUIElements = new GUIClickable[]{menuButton,volumeControl};
        this.visibleGUIElements = new GUIDrawable[]{menuButton,volumeControl,inventory,playerHealth};
        
        
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
        
        lightMap = new FrameBuffer(Format.RGBA4444, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
        lightMapTexture = lightMap.getColorBufferTexture();
    }
    
    //Draws all the DrawableWorldObjects on the current level
    public void drawDrawableObjects(){
        for(DrawableWorldObjects dwo : model.getCurrentLevel().getDrawableObjects()){
                    batch.draw(textureAtlas.findRegion(dwo.getTextureName()), 
                    dwo.getX(),
                    dwo.getY());
        }
    }
    
    public void soundEffects(){
        walking = Gdx.audio.newSound(Gdx.files.internal("walk.wav"));
        running = Gdx.audio.newSound(Gdx.files.internal("run.wav"));
    }
    
    private void render(){
        
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Player player = this.model.getPlayer();
        
        soundEffects();

       
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
        
        // DRAWS TILEMAP
        if(currentLvlTextureName != model.getCurrentLevel().getMapTextureName()){
            currentLvlTextureName = model.getCurrentLevel().getMapTextureName();
            currentLvlTextureAtlas = new TextureAtlas("packed/" +currentLvlTextureName);
        }
        
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
            
            //long id = walking.play();
        } 
        else if(player.isJumping()) {
            batch.draw(textureAtlas.findRegion("playerJump"), player.getX(), player.getY());
        }
        else if(player.getCrouch()){
            batch.draw(textureAtlas.findRegion("playerCrouch1"), player.getX(), player.getY());
        }
        else {
            batch.draw(textureAtlas.findRegion(player.getTextureNameStandStill()),player.getX() ,player.getY());
            //walking.pause();
        }
        
        drawDrawableObjects();
        
       // batch.draw(textureAtlas.findRegion(player.getTextureNameStandStill(), drawableObjects.get(1).getX(), ));
        //batch.draw(textureAtlas.findRegion(player.getTextureName()),player.getX() ,player.getY());
        //batch.draw(textureAtlas.findRegion(player.getTextureNameStandStill()),spikes.getX(), spikes.getY());
        //batch.draw(textureAtlas.findRegion(player.getTextureName()),enemy2.getX(), enemy2.getY());
        batch.end();

        //DRAW FLASHLIGHTCONE
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
        shapeRenderer.rect(0,0, camera.viewportWidth, camera.viewportHeight);
        
        shapeRenderer.flush();
        shapeRenderer.set(ShapeType.Filled);
        
        shapeRenderer.setColor(0, 0, 1, 0);
        float[] polygon = model.getFlashlightPolygon();
        //Must paint it as several triangles instead of a single polygon
        for(int i = 2; i < polygon.length-3; i+=2){
            shapeRenderer.triangle(polygon[0], polygon[1], polygon[i], polygon[i+1], polygon[i+2], polygon[i+3]);
        }
        
        //flush shapeRenderer buffer to frame buffer and stop drawing
        shapeRenderer.end();
        
        //stop drawing to frame buffer
        lightMap.end();
        
        
        //draw to GUI batch to make sure texture always covers screen
        guiBatch.begin();
        //fetch frame buffer as texture and draw to screen
        guiBatch.draw(lightMap.getColorBufferTexture(), //texture
                0, //x
                0, //y
                camera.viewportWidth, //width
                camera.viewportHeight, //height
                0, //srcX
                0, //srcY
                (int)camera.viewportWidth, //srcWidth
                (int)camera.viewportHeight, //srcHeight
                false, //flipX
                true);//flipY
        guiBatch.end();
        //lightMap.dispose();
    }
    

}
