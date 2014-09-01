package com.startlightstudios.learnmylines;

import java.io.IOException;

import android.media.MediaPlayer;
import android.util.Log;

public class SceneAudioPlayer extends MediaPlayer {
	private static final String TAG = "SceneAudioPlayer";

	private boolean mPaused;

	public SceneAudioPlayer()
	{
		mPaused = false;
	}

	public void playLine(String linePath)
	{
		if(!mPaused)
		{
			try
			{
				reset();
				setDataSource(linePath);
				prepare();
				start();
			}
			catch(IOException e)
			{
				Log.i(TAG, "prepare() failed", e);
			}
		}
		else
		{
			start();
			mPaused = false;
		}

	}

	public void pause()
	{
		super.pause();
		mPaused = true;
	}
}
