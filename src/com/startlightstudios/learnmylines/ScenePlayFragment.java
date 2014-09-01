package com.startlightstudios.learnmylines;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learnmylines.R;

public class ScenePlayFragment extends Fragment {
	private Button mPlayButton;
	private SceneAudioPlayer mPlayer;
	private Scene mScene;
	private Button mPauseButton;


	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_scene_play, parent, false);
		
		mScene = EditPlayPagerActivity.SAMPLE_SCENE;
		mPlayer = new SceneAudioPlayer(getActivity(), mScene);
		
		mPlayButton = (Button)v.findViewById(R.id.fragment_scene_play_playButton);
		mPlayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mPlayer.playScene(getActivity(), mScene);
			}
		});
		
		mPauseButton = (Button)v.findViewById(R.id.fragment_scene_play_pauseButton);
		mPauseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mPlayer.pause();
			}
		});
		
		return v;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mPlayer.stop();
	}

	public Scene getScene() {
		return mScene;
	}

	public void setScene(Scene scene) {
		mScene = scene;
	}
	
	
}
