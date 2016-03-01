package com.rabbit.framework.fragments;

import android.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * @author miaohd
 */
public abstract class BaseFragment extends Fragment {

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart(getPageName());
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd(getPageName());
	}

	protected String getPageName(){
		return this.getClass().getSimpleName();
	}

}
