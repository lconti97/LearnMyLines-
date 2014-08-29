package com.startlightstudios.learnmylines;

import java.util.ArrayList;

public class Scene {
	private String title;
	private ArrayList<Integer> lineIds;
	
	public Scene(String t)
	{
		title = t;
		lineIds = new ArrayList<Integer>();
	}
	
	public void addLine(int resId)
	{
		Integer rId = resId;
		lineIds.add(rId);
	}

	public ArrayList<Integer> getLineIds() {
		return lineIds;
	}
	
	
}
