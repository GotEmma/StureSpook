package edu.chl.StureSpook.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.StureSpook.Options;
import edu.chl.StureSpook.model.GameModel;

import edu.chl.StureSpook.model.World;
import edu.chl.StureSpook.view.ProjectView;

public class ProjectController extends ApplicationAdapter{
    private Options options = Options.getInstance();
    private ProjectInputHandler desktopInputHandler;
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
        this.model.initLevels();
        this.view.init();
        Gdx.input.setInputProcessor((InputProcessor) view); 
        
    }

    public ProjectController() {
            model = new World();
            view = new ProjectView(model);
            desktopInputHandler = new ProjectInputHandler(model);
            view.addInputListener(desktopInputHandler);
            model.addPropertyChangeListener(view);

    }

    
    @Override
    public void render () {
        // -do something with time here-
        this.model.update(0.1f);
        // view listens to when model is done updating
        
    }

}