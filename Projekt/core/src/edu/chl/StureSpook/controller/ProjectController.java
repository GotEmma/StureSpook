package edu.chl.StureSpook.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.StureSpook.model.GameModel;

import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;

public class ProjectController extends ApplicationAdapter implements InputProcessor {
    SpriteBatch batch;
    Texture img;
    Texture img2;
    private ProjectView view;
    private GameModel model;
    private boolean jumpPressed;

    @Override
    public void create() {
        this.view.init();
    }

    public ProjectController() {
            model = new Project();
            view = new ProjectView(model);

    }

    
    @Override
    public void render () {
        boolean[] WASD = new boolean[4];
        WASD[0] = this.jumpPressed;
        WASD[1] = Gdx.input.isKeyPressed(Input.Keys.A);
        WASD[2] = Gdx.input.isKeyPressed(Input.Keys.S);
        WASD[3] = Gdx.input.isKeyPressed(Input.Keys.D);
        // -do something with time here-
        this.model.update(0.1f, WASD);
        if (WASD[0]) { this.jumpPressed = false; }
        this.view.render();
        
    }

    @Override
    public boolean keyDown(int i) {
        if (i == Keys.W) {
            this.jumpPressed = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) { return false; }

    @Override
    public boolean keyTyped(char c) { return false; }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) { return false; }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) { return false; }

    @Override
    public boolean touchDragged(int i, int i1, int i2) { return false; }

    @Override
    public boolean mouseMoved(int i, int i1) { return false; }

    @Override
    public boolean scrolled(int i) { return false; }

}
