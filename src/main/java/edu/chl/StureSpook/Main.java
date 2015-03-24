package edu.chl.StureSpook;

import edu.chl.StureSpook.controller.ProjectController;
import edu.chl.StureSpook.model.Project;
import edu.chl.StureSpook.view.ProjectView;

import javax.swing.SwingUtilities;

/*
  Application entry class (if using standard java and Swing)
*/
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
