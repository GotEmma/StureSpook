package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
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
import edu.chl.StureSpook.model.GameModel;
import edu.chl.StureSpook.model.Player;
import edu.chl.StureSpook.model.GameTile;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectView extends InputAdapter implements GameView,PropertyChangeListener{

    private GameModel model;
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private HashMap<String,Sprite> sprites;
    private OrthographicCamera camera;
    private ArrayList<DesktopInputListener> listeners;
    private TextureAtlas textureAtlas;
    private Object[] GUIElements;

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
        this.GUIElements = new Object[2];
        //Add buttons here
    }
    
    @Override
    public void init() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        //renderer.setProjectionMatrix(camera.combined);
        this.loadAssets();
    }
    
    private void render(){
       
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        float cameraX = Math.max(model.getPlayer().getX(),camera.viewportWidth/2); //left limit
        cameraX = Math.min(cameraX, 
                this.model.getCurrentLevel().getWidth()-(camera.viewportWidth/2) );//right limit
        
        float cameraY = Math.max(model.getPlayer().getY(),camera.viewportHeight/2); //bottom limit
        cameraY = Math.min(cameraY, 
                this.model.getCurrentLevel().getHeight()-(camera.viewportHeight/2) );//top limit
        
        camera.position.set(cameraX, cameraY, 100);
        camera.update();
        
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        //DRAW BACKGROUND IMAGE HERE:
        batch.draw(textureAtlas.findRegion(this.model.getCurrentLevel().getMapTextureName()), 0, 0);
        
        //DRAW TILE MAP HERE:
        GameTile[][] tiles = this.model.getTiles();
        for (GameTile[] row : tiles) {
            for (GameTile column : row) {
                //DRAW TILE HERE
            }
        }
        
        //DRAW WORLD OBJECTS - här hamnar spelare, fiender, och objekt på banan, exempelvis.
        Player p = this.model.getPlayer();
        batch.draw(textureAtlas.findRegion(p.getTextureName()),p.getX() ,p.getY());
        
        
        batch.end();
        
        //DRAW FLASHLIGHT HERE
        float[] polygon  = this.model.getFlashlightPolygon(); //Gör något med denna
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setProjectionMatrix(camera.combined);
        renderer.line(polygon[0], polygon[1], polygon[2], polygon[3], Color.MAGENTA, Color.CYAN);//Rita helsvart över skärmen senare, med ett transparent hål som motsvarar ficklampsljus
        renderer.end();
        
        
        
        //DRAW USER INTERFACE HERE
        //work out how interface will work. Only commands will be passed to model!
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
        Vector3 coords = this.getCamera().unproject(new Vector3(x,y,0));
        for(DesktopInputListener l:listeners){
            l.mouseMoved((int)coords.x, (int)coords.y);
        }
        return true; 
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.render();
    }


}
