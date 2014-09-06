package com.starlightstudios.learnmylines;

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
		mAct.getScenes().add(this);
		lines = new ArrayList<Line>();
	}
	
	public void addLine(Line line)
	{
		lines.add(line);
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
	
	public Act getAct()
	{
		return mAct;
	}
	
	public String getTitle()
	{
		return mTitle;
	}
	

}