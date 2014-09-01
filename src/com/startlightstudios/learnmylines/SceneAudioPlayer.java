package com.startlightstudios.learnmylines;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

public class SceneAudioPlayer {
	private static final String TAG = "SceneAudioPlayer";

	private MediaPlayer mPlayer;
	private int mCurrentLineIndex;
	private Scene mScene;
	private Context mContext;
	
	public SceneAudioPlayer(Context c, Scene s)
	{
		mContext = c;
		mScene = s;
		mCurrentLineIndex = 0;
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
		if(mPlayer == null)
		{
			mPlayer = new MediaPlayer();
			try
			{
				mPlayer.setDataSource(linePath);
				mPlayer.prepare();
				mPlayer.start();
			}
			catch(IOException e)
			{
				Log.i(TAG, "prepare() failed", e);
			}
		}
	}

	public void playScene(Context c, Scene s)
	{
		ArrayList<String> linePaths = mScene.getLinePaths();
		playLine(mContext, linePaths.get(mCurrentLineIndex));
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				stop();
				// If the next line exists
				if(mCurrentLineIndex + 1  < mScene.getLineIds().size())
				{
					mCurrentLineIndex++;
					playLine(mContext, mScene.getLineIds().get(mCurrentLineIndex));
				}
				else
				{
					mCurrentLineIndex = 0;
				}
			}
		});
	}
	
	public void pause()
	{
		if (mPlayer == null || !mPlayer.isPlaying())
			return;
		else
		{
			mPlayer.pause();
		}
	}

	public void stop()
	{
		if(mPlayer != null)
		{
			mPlayer.release();
			mPlayer = null;
		}
	}
}
