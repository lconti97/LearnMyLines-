package com.startlightstudios.learnmylines;

import java.util.ArrayList;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.example.learnmylines.R;

public class SceneAudioPlayer {

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
		stop();

		mPlayer = MediaPlayer.create(c, resId);
		mPlayer.start(); 
	}

	public void playScene(Context c, Scene s)
	{
		stop();
		ArrayList<Integer> lineIds = mScene.getLineIds();
		playLine(mContext, lineIds.get(mCurrentLineIndex));
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
			}
		});
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
