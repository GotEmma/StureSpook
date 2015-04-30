package edu.chl.StureSpook.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import edu.chl.StureSpook.Options;
import edu.chl.StureSpook.model.GameModel;

import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;

public class ProjectController extends ApplicationAdapter implements InputProcessor {
    private Options options = Options.getInstance();
    private InputHandler inputHandler = new ProjectInputHandler();
    SpriteBatch batch;
    Texture img;
    Texture img2;
    private ProjectView view;
    private GameModel model;

    @Override
    public void create() {
        options.setWalkLeftKey(Keys.A);
        options.setWalkRightKey(Keys.D);
        options.setJumpKey(Keys.W);
        options.setCrouchKey(Keys.S);
        this.view.init();
        Gdx.input.setInputProcessor(this); //TODO: Sätt till View:en!
    }

    public ProjectController() {
            model = new Project();
            view = new ProjectView(model);

    }

    
    @Override
    public void render () {
        // -do something with time here-
        this.model.update(0.1f);
        this.view.render();
        
    }

    @Override
    public boolean keyDown(int i) {
        inputHandler.keyDown(i, model);
        return true;
    }

    @Override
    public boolean keyUp(int i) { 
        inputHandler.keyUp(i, model);
        return true;
    }

    @Override
    public boolean keyTyped(char c) { return false; }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) { return false; }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) { return false; }

    @Override
    public boolean touchDragged(int i, int i1, int i2) { return false; }

    @Override
    public boolean mouseMoved(int x, int y) { 
        Vector3 coords = view.getCamera().unproject(new Vector3(x,y,0));
        this.inputHandler.mouseMoved((int)coords.x, (int)coords.y, model);
        return true; 
    }

    @Override
    public boolean scrolled(int i) { return false; }

}
