package edu.chl.StureSpook.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.StureSpook.Options;
import edu.chl.StureSpook.model.GameModel;
import edu.chl.StureSpook.model.MainMenuModel;

import edu.chl.StureSpook.model.World;
import edu.chl.StureSpook.view.GameView;
import edu.chl.StureSpook.view.MainMenuView;
import edu.chl.StureSpook.view.ProjectView;

public class ProjectController extends ApplicationAdapter{
    private final Options options = Options.getInstance();
    private ProjectInputHandler desktopInputHandler;
    SpriteBatch batch;
    Texture img;
    Texture img2;
    private GameView view;
    private GameModel model;
    private boolean created;

    @Override
    public void create() {
        Gdx.input.setInputProcessor((InputProcessor) view);
        
        this.model.init();
        this.view.init();
        created = true;
        
    }

    private void startGame() {
        World world = new World();
        model = world;
        view = new ProjectView(world);
        desktopInputHandler = new ProjectInputHandler(world);
        view.addInputListener(desktopInputHandler);
        model.addPropertyChangeListener(view);
        
        options.setWalkLeftKey(Keys.A);
        options.setWalkRightKey(Keys.D);
        options.setJumpKey(Keys.W);
        options.setCrouchKey(Keys.S);
        //options.setCrouchToggle(true);
        created = false;
    }
    
    private void mainMenu() {
        MainMenuModel mainMenuModel = new MainMenuModel();
        model = (GameModel) mainMenuModel;
        view = new MainMenuView(mainMenuModel);
        
        
        created = false;
    }
    
    public ProjectController() {
        //mainMenu();
        startGame();
    }

    
    @Override
    public void render () {
        if (!created) {this.create();}
        this.model.update();
        // view listens to when model is done updating
        
    }

}