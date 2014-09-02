package com.startlightstudios.learnmylines;

import java.util.ArrayList;

public class Scene {
	private String mTitle;
	private ArrayList<Line> lines;
	private Scene.OnDataChangedListener listener;
	private Act mAct;
	
	public Scene(String title, Act act)
	{
		mTitle = title;
		mAct = act;
		lines = new ArrayList<Line>();
	}
	
	public void addLine(String filename)
	{
		lines.add(new Line(filename, this));
		if(listener != null)
		{
			listener.onDataChanged(lines);
		}
	}

	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public void setOnDataChangedListener(OnDataChangedListener l)
	{
		listener = l;
	}
	
	public interface OnDataChangedListener {
		
		public void onDataChanged(ArrayList<Line> lines);
	}

}