package com.startlightstudios.learnmylines;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class SceneAudioPlayer extends MediaPlayer {
	private static final String TAG = "SceneAudioPlayer";

	private MediaPlayer mPlayer;
	private int mCurrentLineIndex;
	private Scene mScene;
	private Context mContext;
	private boolean mPaused;
	
	public SceneAudioPlayer(Context c, Scene s)
	{
		mPaused = false;
		mContext = c;
		mScene = s;
		mCurrentLineIndex = 0;
		mPlayer = new MediaPlayer();
	}

	public void playLine(Context c, int resId)
	{
		if(mPlayer == null)
		{
			mPlayer = MediaPlayer.create(c, resId);
		}
		mPlayer.start(); 
	}
	
	public void playLine(Context c, String linePath)
	{
		if(!mPaused)
		{
			try
			{
				mPlayer.reset();
				mPlayer.setDataSource(linePath);
				mPlayer.prepare();
				mPlayer.start();
			}
			catch(IOException e)
			{
				Log.i(TAG, "prepare() failed", e);
			}
		}
		else
		{
			mPlayer.start();
			mPaused = false;
		}
		
	}

	
	public void pause()
	{
		if (mPlayer == null || !mPlayer.isPlaying())
			return;
		else
		{
			mPlayer.pause();
			mPaused = true;
		}
	}

	public void stop()
	{
		if(mPlayer != null)
		{
			mPlayer.stop();
		}
	}

	public void setOnCompletionListener(
			OnCompletionListener onCompletionListener) {
		mPlayer.setOnCompletionListener(onCompletionListener);
	}
}
