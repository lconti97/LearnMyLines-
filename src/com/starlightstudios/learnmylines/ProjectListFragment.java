package com.starlightstudios.learnmylines;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.learnmylines.R;

public class ProjectListFragment extends Fragment {
	private ArrayList<NLevelItem> list;
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, 
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_project_list, null);

		//TODO: Change listView1's name
		listView = (ListView)v.findViewById(R.id.listView1);
		list = new ArrayList<NLevelItem>();

		final NLevelItem grandParent = new NLevelItem(
				ProjectListActivity.sampleProjectOne,
				null,
				new NLevelView() {

					@Override
					public View getView(NLevelItem item) {
						//TODO: find a different list item style
						View view = getActivity().getLayoutInflater()
								.inflate(R.layout.list_item, null);
						TextView tv = (TextView) view.findViewById(R.id.textView);
						tv.setBackgroundColor(Color.GREEN);
						String name = (String) ((Project) item.getWrappedObject())
								.getTitle();
						tv.setText(name);
						return view;
					}
				});		
		
		list.add(grandParent);
		
		
		
		NLevelAdapter adapter = new NLevelAdapter(list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				((NLevelAdapter)listView.getAdapter()).toggle(arg2);
				((NLevelAdapter)listView.getAdapter()).getFilter().filter();
				
			}
		});
		return v;
	}

}
