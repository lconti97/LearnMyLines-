package com.startlightstudios.learnmylines;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learnmylines.R;

public class SceneEditFragment extends Fragment{
	private static final String TAG = "SceneEditFragment";
	
	private Button mRecordButton;
	private SceneAudioRecorder mRecorder;
	private boolean mRecording = false;
	private String mFileName;
	private Scene mScene;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_scene_edit, parent, false);

		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		mFileName += "/learnmylines.3gp";
		
		mScene = EditPlayPagerActivity.SAMPLE_SCENE;

		mRecordButton = (Button)v.findViewById(R.id.fragment_scene_edit_recordButton);
		mRecordButton.setText(R.string.record);
		mRecordButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!mRecording)
				{
					mRecording = mRecorder.start(mScene);
					if(mRecording)
					{
						mRecordButton.setText(R.string.stop);
					}
					else
					{
						Log.i(TAG, "Recording failed to start");
					}
				}
				else
				{
					stopRecording();
				}
			}
		});

		return v;
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		if(mRecording)
		{
			stopRecording();
		}
		mRecorder.release();
		mRecorder = null;
	}
	
	@Override
	public void onStart()
	{
		super.onResume();
		mRecorder = new SceneAudioRecorder();
	}
	
	private void stopRecording()
	{
		mRecorder.stop();
		mRecorder.reset();
		mRecordButton.setText(R.string.record);
		mRecording = false;
	}
}
