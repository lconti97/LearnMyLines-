package com.startlightstudios.learnmylines;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.learnmylines.R;

public class EditPlayPagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
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
