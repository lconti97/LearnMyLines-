package com.starlightstudios.learnmylines;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
	
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id)
	{
		Project p = (Project) getListAdapter().getItem(pos);
		TextView tv = new TextView(getActivity());
		tv.setText("Scene I");
		LayoutParams params = new LayoutParams(
									LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT);
		
		getActivity().addContentView(tv, params);
	}

}
