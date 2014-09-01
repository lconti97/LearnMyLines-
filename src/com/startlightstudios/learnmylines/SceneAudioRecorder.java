package com.startlightstudios.learnmylines;

import java.io.IOException;

import android.media.MediaRecorder;
import android.util.Log;

public class SceneAudioRecorder extends MediaRecorder {
	private static String TAG = "SceneAudioRecorder";

	private MediaRecorder mRecorder;
	private String mFileName;
	private Scene mScene;

	public SceneAudioRecorder()
	{
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	}

	public boolean start(Scene scene, String filename)
	{
		try
		{
			mScene = scene;
			mFileName = filename;
			mRecorder.setOutputFile(filename);
			mRecorder.prepare();
		}
		catch (IOException e)
		{
			Log.i(TAG, "prepare() failed", e);
			return false;
		}
		mRecorder.start();
		return true;
	}

	@Override
	public void stop()
	{
		if(mRecorder != null)
		{
			mRecorder.stop();
			mRecorder.release();
			mScene.addLine(mFileName);
		}
	}

}
