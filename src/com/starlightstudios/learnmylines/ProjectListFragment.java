package com.starlightstudios.learnmylines;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Typeface;
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
	private static final String TAG = "ProjectListFragment";
	
	private ArrayList<NLevelItem> mList;
	private ListView mListView;
	private ProjectManager mManager;
	private NLevelAdapter mAdapter;

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
		mListView = (ListView)v.findViewById(R.id.listView1);
		mList = new ArrayList<NLevelItem>();
		mManager = ProjectManager.get();

		for(int i = 0; i < mManager.getProjects().size(); i++)
		{
			final Project project = mManager.getProjects().get(i);
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
							String name = project.getTitle();
							tv.setText(name);
							tv.setTypeface(Typeface.DEFAULT_BOLD);
							return view;
						}
					});		
			mList.add(grandParent);
			
			ArrayList<Act> acts = project.getActs();
			for(int j = 0; j < acts.size(); j++)
			{
				final Act act = acts.get(j);
				NLevelItem parent = new NLevelItem(
						act,
						grandParent,
						new NLevelView() {
					
					@Override
					public View getView(NLevelItem item) {
						View view = getActivity().getLayoutInflater().
								inflate(R.layout.list_item, null);
						TextView tv = (TextView) view.findViewById(R.id.textView);
						String name = act.getTitle();
						tv.setText("\t\t" + name);
						return view;
					}
				});
		
				mList.add(parent);
				
				ArrayList<Scene> scenes = act.getScenes();
				for(int n = 0; n < scenes.size(); n++)
				{
					final Scene scene = scenes.get(n);
					NLevelItem child = new NLevelItem(
							scene,
							parent,
							new NLevelView() {
						
						@Override
						public View getView(NLevelItem item) {
							View view = getActivity().getLayoutInflater()
									.inflate(R.layout.list_item, null);
							TextView tv = (TextView) view.findViewById(R.id.textView);
							String name = scene.getTitle();
							tv.setText("\t\t\t\t" + name);
							return view;
						}
					});
				
					mList.add(child);
				}
			}

		}

		mAdapter = new NLevelAdapter(mList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				NLevelItem temp = (NLevelItem) mAdapter.filtered.get(arg2);
				if (temp.getWrappedObject() instanceof Scene)
				{
					Intent i = new Intent(getActivity(), EditPlayPagerActivity.class);
					getActivity().startActivity(i);
				}
				mAdapter.toggle(arg2);
				mAdapter.getFilter().filter();

			}
		});
		return v;
	}

}
