package com.starlightstudios.learnmylines;

import android.support.v4.app.Fragment;

public class ProjectListActivity extends SingleFragmentActivity {
	public static final ProjectManager sManager = ProjectManager.get();
	public static final Project sampleProjectOne = new Project("Romeo and Juliet", sManager);
	public static final Project sampleProjectTwo = new Project("Frankenstein", sManager);
	public static final Act sampleRJActOne = new Act("Act I", sampleProjectOne);
	public static final Act sampleRJActTwo = new Act("Act II", sampleProjectOne);
	public static final Scene sampleRJSceneOne = new Scene("Scene I", sampleRJActOne);
	public static final Scene sampleRJSceneTwo = new Scene("Scene II", sampleRJActTwo);
	
	@Override
	protected Fragment createFragment() {
		createSampleCase();
		return new ProjectListFragment();
	}

	private void createSampleCase()
	{
		ProjectManager manager = ProjectManager.get();
		manager.addProject(sampleProjectOne);
		manager.addProject(sampleProjectTwo);
		sampleProjectOne.addAct(sampleRJActOne);
		sampleProjectOne.addAct(sampleRJActTwo);
		sampleRJActOne.addScene(sampleRJSceneOne);
		sampleRJActTwo.addScene(sampleRJSceneTwo);
	}
}
