package com.starlightstudios.learnmylines;

import java.util.ArrayList;

import com.example.learnmylines.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProjectListFragment extends Fragment {
	private ArrayList<Project> mProjects;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mProjects = ProjectManager.get().getProjects();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, 
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_project_list, null);
		
		return v;
	}

}
