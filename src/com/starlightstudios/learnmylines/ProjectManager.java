package com.starlightstudios.learnmylines;

import java.util.ArrayList;

public class ProjectManager {

	private ArrayList<Project> mProjects;
	private static ProjectManager sManager;
	
	private ProjectManager()
	{
		mProjects = new ArrayList<Project>();
	}
	
	public static ProjectManager get()
	{
		if (sManager == null)
		{
			sManager = new ProjectManager();
		}
		return sManager;
	}
	
	public void addProject(Project project)
	{
		mProjects.add(project);
	}
	
	public ArrayList<Project> getProjects()
	{
		return mProjects;
	}
}
