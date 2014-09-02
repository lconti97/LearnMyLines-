package com.startlightstudios.learnmylines;

import java.util.ArrayList;

public class Act {
	
	private ArrayList<Scene> mScenes;
	private Project mProject;
	private String mTitle;
	
	public Act(String title, Project project)
	{
		mTitle = title;
		mProject = project;
		mScenes = new ArrayList<Scene>();
	}
	
	public void addScene(Scene scene)
	{
		mScenes.add(scene);
	}

	public ArrayList<Scene> getScenes() {
		return mScenes;
	}

	public Project getProject() {
		return mProject;
	}

	public void setProject(Project project) {
		mProject = project;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
	
	
}
