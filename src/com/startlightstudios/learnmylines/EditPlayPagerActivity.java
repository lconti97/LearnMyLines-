package com.startlightstudios.learnmylines;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.example.learnmylines.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditPlayPagerActivity extends FragmentActivity {
	public static final ProjectManager sManager = ProjectManager.get();
	public static final Project sampleProjectOne = new Project("Romeo and Juliet", sManager);
	public static final Project sampleProjectTwo = new Project("Frankenstein", sManager);
	public static final Act sampleRJActOne = new Act("Act I", sampleProjectOne);
	public static final Act sampleRJActTwo = new Act("Act II", sampleProjectOne);
	public static final Scene sampleRJSceneOne = new Scene("Scene I", sampleRJActOne);
	public static final Scene sampleRJSceneTwo = new Scene("Scene II", sampleRJActTwo);
	
	private ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		createSampleCase();

		setContentView(R.layout.activity_edit_play_pager);
		mViewPager = (ViewPager)findViewById(R.id.activity_edit_play_view_pager);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {
				getActionBar().setSelectedNavigationItem(pos);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentPagerAdapter(fm) {

			@Override
			public int getCount() {
				return 2;
			}

			@Override
			public Fragment getItem(int pos) {
				if(pos == 0)
					return new SceneEditFragment();
				else
					return new ScenePlayFragment();
			}
		});

		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener tabListener = new TabListener() {

			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {

			}

			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				mViewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {

			}
		};
		actionBar.addTab(
				actionBar.newTab() 
				.setText(R.string.edit)
				.setTabListener(tabListener));

		actionBar.addTab(
				actionBar.newTab() 
				.setText(R.string.play)
				.setTabListener(tabListener));

	}
	
	private void createSampleCase()
	{
		ProjectManager manager = ProjectManager.get();
		manager.addProject(sampleProjectOne);
		manager.addProject(sampleProjectTwo);
		sampleProjectOne.addAct(sampleRJActOne);
		sampleProjectOne.addAct(sampleRJActTwo);
		sampleRJActOne.addScene(sampleRJSceneOne);
		sampleRJActTwo.addScene(sampleRJSceneTwo);
	}
}
