package edu.chl.StureSpook.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.StureSpook.model.GameModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;

public class ProjectController extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Texture img2;
    private ProjectView view;
    private GameModel model;

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
        // -do something with time here-
        this.model.update(0.1f /*-feed the time thing in here when done-*/);
        this.view.render();
        
    }

}
