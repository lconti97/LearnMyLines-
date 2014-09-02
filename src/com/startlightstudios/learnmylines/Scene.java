package com.startlightstudios.learnmylines;

import java.util.ArrayList;

public class Scene {
	private String title;
	private ArrayList<String> linePaths;
	private Scene.OnDataChangedListener listener;
	
	public Scene(String t)
	{
		title = t;
		linePaths = new ArrayList<String>();
	}
	
	public void addLine(String filename)
	{
		linePaths.add(filename);
		if(listener != null)
		{
			listener.onDataChanged(linePaths);
		}
	}

	public ArrayList<String> getLinePaths() {
		return linePaths;
	}
	
	public void setOnDataChangedListener(OnDataChangedListener l)
	{
		listener = l;
	}
	
	public interface OnDataChangedListener {
		
		public void onDataChanged(ArrayList<String> linePaths);
	}

}