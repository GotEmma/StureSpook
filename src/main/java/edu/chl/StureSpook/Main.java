package edu.chl.StureSpook;

import javax.swing.SwingUtilities;

import edu.chl.StureSpook.controller.ProjectController;
import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;

/*
  Application entry class (if using standard java and Swing)
*/
//TEST
public final class Main {
	private Main() {
		/* No instances allowed! */
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
                    final Project project = new Project();
                    final ProjectView projectView = new ProjectView(project);
                    
                    ProjectController.create(project, projectView);
                    projectView.setVisible(true);
                });
	}
}
