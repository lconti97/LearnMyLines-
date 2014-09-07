package com.starlightstudios.learnmylines;

import java.io.Serializable;
import java.util.ArrayList;

public class Scene implements Serializable{
	private String mTitle;
	private ArrayList<Line> lines;
	private Scene.OnDataChangedListener listener;
	private Act mAct;
	
	public Scene(String title)
	{
		mTitle = title;
		lines = new ArrayList<Line>();
	}
	
	public void setAct(Act act)
	{
		mAct = act;
	}
	
	public void addLine(Line line)
	{
		lines.add(line);
		line.setScene(this);
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
	
	public Project getProject()
	{
		return mAct.getProject();
	}
	
	public ProjectManager getManager()
	{
		return mAct.getManager();
	}
	
	public String getTitle()
	{
		return mTitle;
	}
	

}