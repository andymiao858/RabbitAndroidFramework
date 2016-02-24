package com.rabbit.framework;

import android.app.Application;

import com.android.volley.toolbox.RabbitVolley;
import com.rabbit.framework.config.RabbitConfig;
import com.rabbit.framework.utils.RLog;
import com.umeng.analytics.MobclickAgent;

/**
 * @author miaohd
 */
public class RabbitApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ApplicationKeeper.attach(this);
		initConfig();
		initNetwork();
		initAnalytics();
		RLog.d(BuildConfig.FLAVOR);

	}

	private void initConfig() {
		// init API_DOMAIN & USER_AGENT
		RabbitConfig.getInstance();
	}

	private void initNetwork() {
		// init network volley
		RabbitVolley.newRequestQueue(this);
	}

	private void initAnalytics() {
		MobclickAgent.setDebugMode(!BuildConfig.BUILD_TYPE.equals("release"));
		// disable default page analytics
		MobclickAgent.openActivityDurationTrack(false);
		MobclickAgent.setSessionContinueMillis(30000);

	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ApplicationKeeper.detach();
	}
}
