package com.starlightstudios.learnmylines;

import android.view.View;

public interface NLevelListItem {

	public boolean isExpanded();
	public void toggle();
	public NLevelListItem getParent();
	public View getView();
}
