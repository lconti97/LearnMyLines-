package com.starlightstudios.learnmylines;

import java.util.ArrayList;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.learnmylines.R;
import com.starlightstudios.learnmylines.Scene.OnDataChangedListener;

public class ScenePlayFragment extends Fragment {
	private static final String TAG = "ScenePlayFragment";

	private ImageButton mCenterButton;
	private Button mLeftButton;
	private Button mRightButton;
	private SceneAudioPlayer mPlayer;
	private Scene mScene;
	private boolean mPlaying = false;
	private int mCurrentLineIndex;
	private ListView mLineList;
	private ArrayAdapter<Line> mLineListAdapter;

	private View.OnClickListener mRepeatListener = new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			mPlayer.seekTo(0);
		}
	};
	private View.OnClickListener mNextListener = new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			nextLine();
		}
	};
	private View.OnClickListener mPlayListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(!mPlaying)
			{
				playScene();
//				mCenterButton.setText(R.string.pause);
				mPlaying = true;
			}
			else
			{
				mPlayer.pause();
//				mCenterButton.setText(R.string.play);
				mPlaying = false;
			}
		}
	};
	private View.OnClickListener mCheckLineListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
	};

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState)
	{
		setHasOptionsMenu(true);

		int[] sceneIndex = getActivity().getIntent().
				getIntArrayExtra(ProjectListFragment.EXTRA_SCENE_INDEX);
		mScene = ProjectManager.get()
				.getProjects().get(sceneIndex[0])
				.getActs().get(sceneIndex[1])
				.getScenes().get(sceneIndex[2]);

		View v = inflater.inflate(R.layout.fragment_scene_play, parent, false);

		mLineList = (ListView) v.findViewById(R.id.fragment_scene_play_lineList);
		EditPlayPagerActivity a = (EditPlayPagerActivity)getActivity();
		mLineListAdapter = a.getLineListAdapter();
		mLineList.setAdapter(mLineListAdapter);

		mCurrentLineIndex = 0;


		mLeftButton = (Button)v.findViewById(R.id.fragment_scene_play_buttonBar)
				.findViewById(R.id.button_left);
		mCenterButton = (ImageButton)v.findViewById(R.id.fragment_scene_play_buttonBar)
				.findViewById(R.id.button_center);
		mRightButton = (Button)v.findViewById(R.id.fragment_scene_play_buttonBar)
				.findViewById(R.id.button_right);

		switchButtons();

		if(mScene.getLines().size() == 0)
		{
			mCenterButton.setEnabled(false);
		}
		else
		{
			mCenterButton.setEnabled(true);
		}

		mScene.setOnDataChangedListener(new OnDataChangedListener() {

			@Override
			public void onDataChanged(ArrayList<Line> linePaths) {
				if(linePaths.size() == 0)
				{
					mCenterButton.setEnabled(false);
				}
				else
				{
					mCenterButton.setEnabled(true);
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
				nextLine();
			}
		});
	}

	private void nextLine() {
		mPlayer.stop();
		// If the next line exists
		if(mCurrentLineIndex + 1  < mScene.getLines().size())
		{
			mCurrentLineIndex++;
			switchButtons();
			if(!isMyLine()){ 
				mPlayer.playLine(mScene.getLines().get(mCurrentLineIndex));
			}
		}
		else
		{
			onPlayComplete();
			mCurrentLineIndex = 0;
		}
	}

	private void switchButtons() {
		if(isMyLine()) {
			mLeftButton.setText(R.string.repeat);
//			mCenterButton.setText(R.string.next);
			mRightButton.setText(R.string.check_line);

			mLeftButton.setOnClickListener(mRepeatListener);
			mCenterButton.setOnClickListener(mNextListener);
			mRightButton.setOnClickListener(mCheckLineListener);
		}
		else {
			mLeftButton.setText(R.string.repeat);
			if(!mPlaying) {}
//				mCenterButton.setText(R.string.play);
			else {}
//				mCenterButton.setText(R.string.pause);
			mRightButton.setText(R.string.next);

			mLeftButton.setOnClickListener(mRepeatListener);
			mCenterButton.setOnClickListener(mPlayListener);
			mRightButton.setOnClickListener(mNextListener);
		}
	}

	private boolean isMyLine() {
		if(mCurrentLineIndex < mScene.getLines().size() &&
				mScene.getLines().get(mCurrentLineIndex).getSpeaker() == getString(R.string.me))
			return true;
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
			}
			return true;
		default: 
			return super.onOptionsItemSelected(item);
		}
	};

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
//		mCenterButton.setText(R.string.play);
		mPlaying = false;
	}

	public Scene getScene() {
		return mScene;
	}

	public void setScene(Scene scene) {
		mScene = scene;
	}


}
