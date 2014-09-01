package com.startlightstudios.learnmylines;

import java.util.ArrayList;

import org.apache.http.message.LineParser;

public class Scene {
	private String title;
	private ArrayList<Integer> lineIds;
	private ArrayList<String> linePaths;
	
	public Scene(String t)
	{
		title = t;
		lineIds = new ArrayList<Integer>();
		linePaths = new ArrayList<String>();
	}
	
	public void addLine(int resId)
	{
		Integer rId = resId;
		lineIds.add(rId);
	}
	
	public void addLine(String filename)
	{
		linePaths.add(filename);
	}

	public ArrayList<Integer> getLineIds() {
		return lineIds;
	}

	public ArrayList<String> getLinePaths() {
		return linePaths;
	}
	
	
	
	
}
