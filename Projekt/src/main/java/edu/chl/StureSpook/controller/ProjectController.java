package edu.chl.StureSpook.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class ProjectController extends BasicGame{

        private ProjectView view;
        private Project model;

	public static ProjectController create(String name) {
		return new ProjectController(name);
	}

	private ProjectController(String name) {
                super(name);
                model = new Project();
		view = new ProjectView(model);
                
	}

    @Override
    public void init(GameContainer gc) throws SlickException {
        // Here should later resources be loaded and the game state initialized.
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        model.update();
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        view.render();
    }

}
