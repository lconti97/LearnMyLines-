package com.startlightstudios.learnmylines;

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

	public void setScene(Scene scene) {
		mScene = scene;
	}
	
	
}
