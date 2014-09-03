package com.starlightstudios.learnmylines;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class ProjectListFragment extends ListFragment {
	private ArrayList<Project> mProjects;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mProjects = ProjectManager.get().getProjects();
		
		ArrayAdapter<Project> adapter = 
				new ArrayAdapter<Project>(getActivity(),
						android.R.layout.simple_list_item_1,
						mProjects);
		
		setListAdapter(adapter);
	}

}
