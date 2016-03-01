package com.rabbit.framework;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RabbitVolley;
import com.igexin.sdk.PushManager;
import com.rabbit.framework.config.RabbitConfig;
import com.rabbit.framework.utils.RLog;
import com.rabbit.framework.utils.ScreenUtil;
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
		initPush();
		initAnalytics();
		RLog.d(BuildConfig.FLAVOR);

	}

	private void initConfig() {
		// init API_DOMAIN & USER_AGENT
		RabbitConfig.getInstance();
		ScreenUtil.init(this.getApplicationContext());
	}

	private void initNetwork() {
		// init network volley
		RequestQueue requestQueue = RabbitVolley.newRequestQueue(this);
		// Glide integrate with Volley
//		Glide.get(this).register(GlideUrl.class, InputStream.class, new VolleyUrlLoader.Factory(requestQueue));
		// Glide integrate with Okhttp3
//		Glide.get(this).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient()));
	}

	private void initPush(){
		// 该方法必须在Activity或Service类内调用，一般情况下，可以在Activity的onCreate()方法中调用。
		// 由于应用每启动一个新的进程，就会调用一次Application的onCreate()方法，
		// 而个推SDK是一个独立的进程，因此如果在Application的onCreate()中调用intialize接口，
		// 会导致SDK初始化在一个应用中多次调用，所以不建议在Application继承类中调用个推SDK初始化接口。
		PushManager.getInstance().initialize(this.getApplicationContext());
		int startTime = Integer.valueOf(this.getResources().getString(R.string.getui_silent_time_start));
		int duration = Integer.valueOf(this.getResources().getString(R.string.getui_duration));
		PushManager.getInstance().setSilentTime(this, startTime, duration);
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
