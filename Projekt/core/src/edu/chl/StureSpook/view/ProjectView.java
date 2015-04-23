package edu.chl.StureSpook.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.StureSpook.model.Drawable;
import edu.chl.StureSpook.model.GameModel;
import java.util.HashMap;

public class ProjectView implements GameView{

    private GameModel model;
    private SpriteBatch batch;
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
    public void init() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        this.loadAssets();
    }
    
    @Override
    public void render(){
        //Clear screen so that next frame is drawn on a clean slate
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        batch.begin();
        //batch.draw(img, 0, 0);
        //TODO: REFAKTORERA DETTA SÅ ATT draw() FINNS SOM METOD I Drawable-OBJEKT! VIKTIKGT!
        Drawable[] images = model.getImages();
        for(Drawable i : images){
            //batch.draw(this.textures.get(i.getTextureName()), i.getX(), i.getY());
            i.draw(batch, this.textures);
        }
        batch.end();
        
    }

}
