package com.starlightstudios.learnmylines;

public class Line {

	private String mFileName;
	private Scene mScene;
	
	public Line(String filename, Scene scene)
	{
		mFileName = filename;
		mScene = scene;
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
	
	
}
