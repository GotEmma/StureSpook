package edu.chl.StureSpook.view;

import edu.chl.StureSpook.model.Project;

public class ProjectView {

    private Project model;

    public ProjectView(Project model) {
        this.model = model;
    }
    
    public void render(){
        /*Image[] images = model.getImages();
        for(int i = 0; i < images.length; i++){
            images[i].draw();
        }*/
    }

}
