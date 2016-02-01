/*
 * Copyright [Rabbit]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rabbit.framework.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.rabbit.framework.ApplicationKeeper;
import com.rabbit.framework.BuildConfig;

import java.util.Locale;
import java.util.TimeZone;

/**
 * @author miaohd
 */
public class RabbitConfig {

	private RabbitConfig(){

	}

	public static RabbitConfig getInstance(){
		return RabbitConfigBuilder._instance;
	}

	public String getApiDomain(){
		return RabbitConfigBuilder.API_DOMAIN;
	}

	public String getUserAgent(){
		return RabbitConfigBuilder.USER_AGENT;
	}

	private static class RabbitConfigBuilder{
		private static RabbitConfig _instance = new RabbitConfig();
		private static final String API_DOMAIN;
		private static final String USER_AGENT;

		static {
			API_DOMAIN = BuildConfig.PHASE.getApiDomain();
			USER_AGENT = getUserAgent(ApplicationKeeper.getApplication());
		}


		private static String getUserAgent(Context context) {
			String appVersion = null;
			int buildVersion = 0;
			String locale;
			String language;
			String timezone;
			float density;
			int width;
			int height;
			String cellular;
			String manufacture;
			String device;
			String osVersion;

			PackageManager packageManager = context.getPackageManager();
			try {
				PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
				appVersion = packageInfo.versionName;
				buildVersion = packageInfo.versionCode;
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}

			DisplayMetrics metrics = context.getResources().getDisplayMetrics();
			TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

			locale = Locale.getDefault().toString();
			language = Locale.getDefault().getLanguage();
			timezone = TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
			density = metrics.density;
			width = metrics.widthPixels;
			height = metrics.heightPixels;
			cellular = tm.getNetworkOperatorName();
			manufacture = Build.MANUFACTURER;
			device = Build.MODEL;
			osVersion = Build.VERSION.RELEASE;

			return new StringBuilder()
					.append("TLAV/").append(appVersion).append(";")
					.append("TLBV/").append(buildVersion).append(";")
					.append("TLLC/").append(locale).append(";")
					.append("TLTZ/").append(timezone).append(";")
					.append("TLLA/").append(language).append(";")
					.append("TLDM/").append("{density=" + density + ",width=" + width + ",height=" + height + "}").append(";")
					.append("TLCR/").append(cellular).append(";")
					.append("TLMF/").append(manufacture).append(";")
					.append("TLDV/").append(device).append(";")
					.append("TLDO/").append("Android_" + osVersion).append(";")
					.toString();
		}

	}



}
