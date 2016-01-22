package com.rabbit.framework.fragments;

import de.greenrobot.event.EventBus;

/**
 * @author miaohd
 */
public abstract class BaseSubscriberFragment extends BaseFragment{

	@Override
	public void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);
		super.onStop();
	}
}
