package edu.chl.StureSpook;

import javax.swing.SwingUtilities;

import edu.chl.StureSpook.controller.ProjectController;
import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/*
  Application entry class (if using standard java and Swing)
*/
//TEST
public final class Main {
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
                
                try{
                    AppGameContainer container = new AppGameContainer(ProjectController.create("StureSpook"));
                    container.setDisplayMode(800,600,false);
                    container.start();
                }catch (SlickException e){
                    e.printStackTrace();
                }
	}
}
