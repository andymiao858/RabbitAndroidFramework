package com.rabbit.framework;

import android.app.Application;

import com.android.volley.toolbox.RabbitVolley;
import com.rabbit.framework.config.RabbitConfig;
import com.rabbit.framework.utils.RLog;

/**
 * @author miaohd
 */
public class RabbitApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ApplicationKeeper.attach(this);

		// init API_DOMAIN & USER_AGENT
		RabbitConfig.getInstance();
		// init network volley
		RabbitVolley.newRequestQueue(this);
		RLog.d(BuildConfig.FLAVOR);

	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		ApplicationKeeper.detach();
	}
}
