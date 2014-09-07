package com.starlightstudios.learnmylines;

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
import com.starlightstudios.learnmylines.Scene.OnDataChangedListener;

public class ScenePlayFragment extends Fragment {
	private static final String TAG = "ScenePlayFragment";
	
	private Button mPlayPauseButton;
	private SceneAudioPlayer mPlayer;
	private Scene mScene;
	private boolean mPlaying = false;
	private int mCurrentLineIndex;

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		int[] sceneIndex = getActivity().getIntent().
				getIntArrayExtra(ProjectListFragment.EXTRA_SCENE_INDEX);
		mScene = ProjectManager.get()
				.getProjects().get(sceneIndex[0])
				.getActs().get(sceneIndex[1])
				.getScenes().get(sceneIndex[2]);
		
		View v = inflater.inflate(R.layout.fragment_scene_play, parent, false);

		mCurrentLineIndex = 0;

		mPlayPauseButton = (Button)v.findViewById(R.id.fragment_scene_play_playButton);
		//if mPlaying, the fragment has been rotated 
		if(!mPlaying)
		{
			mPlayPauseButton.setText(R.string.play);
		}
		else
		{
			mPlayPauseButton.setText(R.string.pause);
		}
		if(mScene.getLines().size() == 0)
		{
			mPlayPauseButton.setEnabled(false);
		}
		else
		{
			mPlayPauseButton.setEnabled(true);
		}
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
		
		mScene.setOnDataChangedListener(new OnDataChangedListener() {
			
			@Override
			public void onDataChanged(ArrayList<Line> linePaths) {
				if(linePaths.size() == 0)
				{
					mPlayPauseButton.setEnabled(false);
				}
				else
				{
					mPlayPauseButton.setEnabled(true);
				}
			}
		});

		return v;
	}


	private void playScene()
	{
		ArrayList<Line> lines = mScene.getLines();
		mPlayer.playLine(lines.get(mCurrentLineIndex));
		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				mPlayer.stop();
				// If the next line exists
				if(mCurrentLineIndex + 1  < mScene.getLines().size())
				{
					mCurrentLineIndex++;
					mPlayer.playLine(mScene.getLines().get(mCurrentLineIndex));
				}
				else
				{
					onPlayComplete();
					mCurrentLineIndex = 0;
				}
			}
		});
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if(mPlaying)
		{
			mPlayer.stop();
			onPlayComplete();
		}
		mPlayer.release();
		mPlayer = null;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		mPlayer = new SceneAudioPlayer();
	}

	private void onPlayComplete()
	{
		mPlayPauseButton.setText(R.string.play);
		mPlaying = false;
	}

	public Scene getScene() {
		return mScene;
	}

	public void setScene(Scene scene) {
		mScene = scene;
	}


}
