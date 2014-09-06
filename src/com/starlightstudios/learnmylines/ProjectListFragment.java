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
	private ProjectManager manager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup vg, 
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_project_list, null);

		//TODO: Change listView1's name
		listView = (ListView)v.findViewById(R.id.listView1);
		list = new ArrayList<NLevelItem>();
		manager = ProjectManager.get();

		for(int i = 0; i < manager.getProjects().size(); i++)
		{
			final Project project = manager.getProjects().get(i);
			final NLevelItem grandParent = new NLevelItem(
					project,
					null,
					new NLevelView() {

						@Override
						public View getView(NLevelItem item) {
							//TODO: find a different list item style
							View view = getActivity().getLayoutInflater()
									.inflate(R.layout.list_item, null);
							TextView tv = (TextView) view.findViewById(R.id.textView);
							tv.setBackgroundColor(Color.GREEN);
							String name = project.getTitle();
							tv.setText(name);
							return view;
						}
					});		
			list.add(grandParent);
			
			final ArrayList<Act> acts = project.getActs();
			for(int j = 0; j < acts.size(); j++)
			{
				final Act act = acts.get(i);
				NLevelItem parent = new NLevelItem(
						act,
						grandParent,
						new NLevelView() {
					
					@Override
					public View getView(NLevelItem item) {
						View view = getActivity().getLayoutInflater().
								inflate(R.layout.list_item, null);
						TextView tv = (TextView) view.findViewById(R.id.textView);
						tv.setBackgroundColor(Color.YELLOW);
						String name = act.getTitle();
						tv.setText(name);
						return view;
					}
				});
		
				list.add(parent);
			}

		}

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
