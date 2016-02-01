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
package com.rabbit.framework;

import android.app.Application;
import android.content.Context;

import com.rabbit.framework.utils.log.RLog;

/**
 * @author miaohd
 */
public class ApplicationKeeper {

	private static final String TAG = "ApplicationKeeper";

	private static Application app;

	public static void attach(Application application){
		if (app == null){
			synchronized (app){
				if (app == null){
					app = application;
				}
			}
		} else {
			RLog.w(TAG, "Application has been attached.");
		}
	}

	public static void detach(){
		if (app != null){
			synchronized (app){
				if (app != null){
					app = null;
				}
			}
		} else {
			RLog.w(TAG, "Application has been detached.");
		}
	}

	public static Application getApplication() {
		if (app != null) {
			return app;
		}
		throw new RuntimeException("Application has not been attached");
	}

	public static Context getApplicationContext(){
		if (app != null){
			return app.getApplicationContext();
		}
		throw new RuntimeException("Applicaton has not been attached.");
	}
}
