package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.Batch;
import edu.chl.StureSpook.model.GameModel;
import edu.chl.StureSpook.model.Player;
import edu.chl.StureSpook.model.GameTile;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectView extends InputAdapter implements GameView,PropertyChangeListener{

    private GameModel model;
    private OrthogonalTiledMapRenderer mapRenderer;
    private SpriteBatch batch,guiBatch;
    private ShapeRenderer shapeRenderer;
    private HashMap<String,Sprite> sprites;
    private OrthographicCamera camera;
    private ArrayList<DesktopInputListener> listeners;
    private TextureAtlas textureAtlas;
    private GUIButton[] GUIElements;
    private int screenMouseX, screenMouseY;

    public ProjectView(GameModel model) {
        this.model = model;
        listeners = new ArrayList<DesktopInputListener>();
    }
    
    private void loadAssets() {
        
        textureAtlas = new TextureAtlas("packed/texturePack1.pack");
        
        
        sprites = new HashMap<String,Sprite>();
    }
    
    @Override
    public OrthographicCamera getCamera() {
        return this.camera;
    }
    
    private void buildGUI() {
        this.GUIElements = new GUIButton[2];
        this.GUIElements[0] = new GUIButton("menu","menuButton","menuButtonMouseover",camera.viewportWidth-64,0,32,16);
        this.GUIElements[1] = new GUIButton("toggleSound","soundButton","soundButtonMuted",camera.viewportWidth-32,0,32,16);
    }
    
    private void doGUIAction(String command) {
        System.out.println("Command pressed: " + command);
    }
    
    @Override
    public void init() {
        mapRenderer = new OrthogonalTiledMapRenderer(model.getCurrentLevel().getMap());
        batch = new SpriteBatch();
        guiBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        //renderer.setProjectionMatrix(camera.combined);
        this.loadAssets();
        buildGUI();
    }
    
    private void render(){
       
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Player player = this.model.getPlayer();
        float cameraX = Math.max(player.getX(),camera.viewportWidth/2); //left limit
        cameraX = Math.min(cameraX, 
                this.model.getCurrentLevel().getWidth()-(camera.viewportWidth/2) );//right limit
        
        float cameraY = Math.max(player.getY(),camera.viewportHeight/2); //bottom limit
        cameraY = Math.min(cameraY, 
                this.model.getCurrentLevel().getHeight()-(camera.viewportHeight/2) );//top limit
        
        camera.position.set(cameraX, cameraY, 100);
        camera.update();
        
        mapRenderer.setView(camera);
        
        // DRAWS BACKGROUND
	batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(textureAtlas.findRegion(this.model.getCurrentLevel().getMapTextureName()), 0, 0);
        batch.end();
        
        // DRAWS TILEMAP
        if(mapRenderer.getMap()!=model.getCurrentLevel().getMap()){
            mapRenderer.setMap(model.getCurrentLevel().getMap());
        }
        mapRenderer.render();
        
        // DRAWS PLAYER + Other Objects
        batch.begin(); 
        batch.draw(textureAtlas.findRegion(player.getTextureName()),player.getX() ,player.getY());
        batch.end();
        
        //DRAW FLASHLIGHT HERE
        float[] polygon  = this.model.getFlashlightPolygon(); //Gör något med denna
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.line(polygon[0], polygon[1], polygon[2], polygon[3], Color.MAGENTA, Color.CYAN);//Rita helsvart över skärmen senare, med ett transparent hål som motsvarar ficklampsljus
        shapeRenderer.end();
        
        
        
        //DRAW USER INTERFACE HERE
        /*for (GUIButton b : GUIElements) {
            b.draw(batch, textureAtlas, screenMouseX, screenMouseY);
        }*/
        guiBatch.begin();
        for (GUIButton b : GUIElements) {
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
        System.out.println("Mouse coords: " + screenMouseX + ", " + screenMouseY);
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
            //System.out.println(x + ", " + y);
            for (GUIButton b:GUIElements) {
                if (b.isClickInBoundaries(x, y)) {
                    doGUIAction(b.getCommand());
                }
            }
        }
        
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.render();
    }


}
