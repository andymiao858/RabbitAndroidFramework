package com.rabbit.framework.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;

public class ScreenUtil {
	static public float mDensity = 2f;
	static public int mWidth = 0;
	static public int mHeight = 0;
	static public int mSlop;
	static public int mNotificationBarHeight;
	static public int mDensityDPI = DisplayMetrics.DENSITY_HIGH;

	static public void init(Context context) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		mDensity = metrics.density;
		mWidth = metrics.widthPixels;
		mHeight = metrics.heightPixels;
		mDensityDPI = metrics.densityDpi;
		mSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mNotificationBarHeight = getNotificationBarHeight(context);
	}

	static public int dp2px(float dp) {
		int px = Math.round(mDensity * dp);
		if (0 < dp && px == 0) {
			px = 1;
		}
		return px;
	}

	static public float px2dp(int px) {
		float dp = (px / mDensity);
		return (dp);
	}

	static public boolean isMedDPI() {
		return (mDensityDPI == DisplayMetrics.DENSITY_MEDIUM);
	}

	static public boolean isLandscape(Context context) {
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return (true);
		}
		return (false);
	}

	static public boolean isPortrait(Context context) {
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			return (true);
		}
		return (false);
	}

	static public int getWidth(Context context) {
		return (context.getResources().getDisplayMetrics().widthPixels);
	}

	static public int getHeight(Context context) {
		return (context.getResources().getDisplayMetrics().heightPixels);
	}

	static public int getNotificationBarHeight(Context context) {
		int notificationBarResources[] = {android.R.drawable.stat_sys_phone_call,
			android.R.drawable.stat_notify_call_mute, android.R.drawable.stat_notify_sdcard,
			android.R.drawable.stat_notify_sync, android.R.drawable.stat_notify_missed_call,
			android.R.drawable.stat_sys_headset, android.R.drawable.stat_sys_warning};
		int notificationBarHeight = -1;
		for (int i = 0; i < notificationBarResources.length; i++) {
			try {
				Drawable phoneCallIcon = context.getResources().getDrawable(notificationBarResources[i]);
				if ((notificationBarHeight = phoneCallIcon.getIntrinsicHeight()) != -1) {
					break;
				}
			} catch (Resources.NotFoundException e) {

			}
		}
		return notificationBarHeight;
	}

	public static int getStatusBarHeight(Context context) {
		if (context == null) {
			return 0;
		}
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}

	public static int getNavigationBarHeight(Context context) {
		if (context == null) {
			return 0;
		}
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			return resources.getDimensionPixelSize(resourceId);
		}
		return 0;
	}
}
