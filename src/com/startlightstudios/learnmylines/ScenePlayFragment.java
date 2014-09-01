package com.startlightstudios.learnmylines;

import java.util.ArrayList;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learnmylines.R;

public class ScenePlayFragment extends Fragment {
	private Button mPlayPauseButton;
	private SceneAudioPlayer mPlayer;
	private Scene mScene;
	private boolean mPlaying = false;
	private int mCurrentLineIndex;


	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_scene_play, parent, false);

		mScene = EditPlayPagerActivity.SAMPLE_SCENE;
		mCurrentLineIndex = 0;
		mPlayer = new SceneAudioPlayer(getActivity(), mScene);

		mPlayPauseButton = (Button)v.findViewById(R.id.fragment_scene_play_playButton);
		mPlayPauseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!mPlaying)
				{
					playScene();
					mPlayPauseButton.setText(R.string.pause);
					mPlaying = true;
				}
				else
				{
					mPlayer.pause();
					mPlayPauseButton.setText(R.string.play);
					mPlaying = false;
				}
			}
		});

		return v;
	}


	public void playScene()
	{
		ArrayList<String> linePaths = mScene.getLinePaths();
		mPlayer.playLine(getActivity(), linePaths.get(mCurrentLineIndex));
		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mPlayer.stop();
				// If the next line exists
				if(mCurrentLineIndex + 1  < mScene.getLineIds().size())
				{
					mCurrentLineIndex++;
					mPlayer.playLine(getActivity(), mScene.getLineIds().get(mCurrentLineIndex));
				}
				else
				{
					mPlayPauseButton.setText(R.string.play);
					mCurrentLineIndex = 0;
					mPlaying = false;
				}
			}
		});
	}

	@Override
	public void onPause()
	{
		super.onPause();
		mPlayer.release();
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
