package com.startlightstudios.learnmylines;

import java.io.IOException;

import android.media.MediaRecorder;
import android.util.Log;

public class SceneAudioRecorder extends MediaRecorder {
	private static String TAG = "SceneAudioRecorder";

	private String mFileName;
	private Scene mScene;

	public SceneAudioRecorder()
	{
		super();
		setAudioSource(MediaRecorder.AudioSource.MIC);
		setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	}

	public boolean start(Scene scene, String filename)
	{
		try
		{
			mScene = scene;
			mFileName = filename;
			setOutputFile(filename);
			prepare();
		}
		catch (IOException e)
		{
			Log.e(TAG, "prepare() failed", e);
			return false;
		}
		start();
		return true;
	}

	@Override
	public void stop()
	{
		super.stop();
		mScene.addLine(mFileName);
	}

}
