package edu.chl.StureSpook.controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;

public class ProjectController extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private ProjectView view;
    private Project model;


    public static ProjectController create(String name) {
            return new ProjectController(name);
    }

    private ProjectController(String name) {
            model = new Project();
            view = new ProjectView(model);

    }

    @Override
    public void render () {
        // TODO: Det här skall väl typ vara i view:en?
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();
    }

}
