package com.startlightstudios.learnmylines;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.learnmylines.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class EditPlayPagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener tabListener = new TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
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
		
		setContentView(R.layout.activity_edit_play_pager);
		mViewPager = (ViewPager)findViewById(R.id.activity_edit_play_view_pager);
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return 2;
			}
			
			@Override
			public Fragment getItem(int pos) {
				return new ScenePlayFragment();
			}
		});
	}
}
