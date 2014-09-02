package com.startlightstudios.learnmylines;

import java.util.ArrayList;

public class Scene {
	private String title;
	private ArrayList<String> linePaths;
	
	public Scene(String t)
	{
		title = t;
		linePaths = new ArrayList<String>();
	}
	
	public void addLine(String filename)
	{
		linePaths.add(filename);
	}

	public ArrayList<String> getLinePaths() {
		return linePaths;
	}
	
	
	
	
}
