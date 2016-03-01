package com.rabbit.framework.fragments;


import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

/**
 * @author miaohd
 */
public abstract class BaseEventFragment extends BaseFragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
