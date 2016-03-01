package com.rabbit.framework.activities;

import android.os.Bundle;

import org.greenrobot.eventbus.EventBus;

/**
 * @author miaohd
 */
public abstract class BaseEventActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
}
