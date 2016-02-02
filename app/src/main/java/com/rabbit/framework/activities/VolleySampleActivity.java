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
package com.rabbit.framework.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.rabbit.framework.R;
import com.rabbit.framework.api.SearchApi;
import com.rabbit.framework.utils.RLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author miaohd
 */
public class VolleySampleActivity extends Activity {

	@Bind(R.id.search)
	Button searchButton;

	@Bind(R.id.showWindow)
	TextView web;

	@OnClick(R.id.search)
	void search(){
		SearchApi.search("RabbitAndroidFramework", String.class, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				RLog.d(response);
				web.setMovementMethod(ScrollingMovementMethod.getInstance());
				web.setText(Html.fromHtml(response));
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				RLog.e(error.getMessage());
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_volley);
		ButterKnife.bind(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
	}
}
