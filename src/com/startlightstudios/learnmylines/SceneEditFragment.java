package com.startlightstudios.learnmylines;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.learnmylines.R;

public class SceneEditFragment extends Fragment{
	private Button mRecordButton;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.fragment_scene_edit, parent, false);

		mRecordButton = (Button)v.findViewById(R.id.fragment_scene_edit_recordButton);
		mRecordButton.setText(R.string.record);
		mRecordButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
			}
		});

		return v;
	}
}
