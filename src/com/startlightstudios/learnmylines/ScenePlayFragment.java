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
		
		mScene = new Scene("Scene I");
		mPlayer = new SceneAudioPlayer(getActivity(), mScene);
		mScene.addLine(R.raw.voice0150);
		mScene.addLine(R.raw.voice0151);
		
		mPlayButton = (Button)v.findViewById(R.id.fragment_scene_play_playButton);
		mPlayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mPlayer.playScene(getActivity(), mScene);
			}
		});
		
		mPauseButton = (Button)v.findViewById(R.id.fragment_scene_play_pauseButton);
		
		return v;
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		mPlayer.stop();
	}
}
