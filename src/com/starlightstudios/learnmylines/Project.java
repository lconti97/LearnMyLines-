package com.starlightstudios.learnmylines;

import java.util.ArrayList;

public class Project {

	private ProjectManager mManager;
	private ArrayList<Act> mActs;
	private String mTitle;
	
	public Project(String title, ProjectManager manager)
	{
		mTitle = title;
		mManager = manager;
		mActs = new ArrayList<Act>();
	}
	
	public void addAct(Act act)
	{
		mActs.add(act);
	}

	public ProjectManager getManager() {
		return mManager;
	}

	public void setManager(ProjectManager manager) {
		mManager = manager;
	}

	public ArrayList<Act> getActs() {
		return mActs;
	}

	public void setActs(ArrayList<Act> acts) {
		mActs = acts;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}
	
	@Override
	public String toString()
	{
		return mTitle;
	}
	
	
}
