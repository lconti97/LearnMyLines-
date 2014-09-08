package com.starlightstudios.learnmylines;

public class Line {

	private String mFileName;
	private Scene mScene;
	private String mTitle;
	
	public Line(String filename, Scene scene, String title)
	{
		mFileName = filename;
		mScene = scene;
		mTitle = title;
	}

	public String getFileName() {
		return mFileName;
	}

	public void setFileName(String fileName) {
		mFileName = fileName;
	}

	public Scene getScene() {
		return mScene;
	}
	
	public Act getAct()
	{
		return mScene.getAct();
	}
	
	public Project getProject()
	{
		return mScene.getProject();
	}
	
	public ProjectManager getManager()
	{
		return mScene.getManager();
	}

	public void setScene(Scene scene) {
		mScene = scene;
	}
	
	public String toString()
	{
		return mTitle;
	}
	
}
