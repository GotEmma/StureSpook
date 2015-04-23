package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import edu.chl.StureSpook.model.DrawableShape;
import edu.chl.StureSpook.model.DrawableSprite;
import edu.chl.StureSpook.model.GameModel;
import java.util.HashMap;

public class ProjectView implements GameView{

    private GameModel model;
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private HashMap<String,Texture> textures;
    private OrthographicCamera camera;

    public ProjectView(GameModel model) {
        this.model = model;
    }
    
    private void loadAssets() {
        textures = new HashMap<String,Texture>();
        textures.put("badlogic", new Texture("badlogic.jpg"));
        textures.put("player", new Texture("player.bmp"));
    }
    
    @Override
    public OrthographicCamera getCamera() {
        return this.camera;
    }
    
    @Override
    public void init() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        renderer.setProjectionMatrix(camera.combined);
        this.loadAssets();
    }
    
    @Override
    public void render(){
        //Clear screen so that next frame is drawn on a clean slate
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        batch.begin();
        DrawableSprite[] images = model.getSprites();
        for(DrawableSprite i : images){
            //batch.draw(this.textures.get(i.getTextureName()), i.getX(), i.getY());
            i.draw(batch, this.textures);
        }
        batch.end();
        
        renderer.begin();
        DrawableShape[] shapes = model.getShapes();
        for (DrawableShape s : shapes) {
            s.draw(renderer);
        }
        renderer.end();
        
    }


}
