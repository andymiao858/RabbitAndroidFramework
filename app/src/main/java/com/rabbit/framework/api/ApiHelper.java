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
package com.rabbit.framework.api;

import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.RabbitGsonRequest;
import com.android.volley.toolbox.RabbitStringRequest;
import com.android.volley.toolbox.RabbitVolley;
import com.rabbit.framework.config.RabbitConfig;
import com.rabbit.framework.utils.StringUtils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author miaohd
 */
public final class ApiHelper {

	private static final String KEY_AGENT = "User-Agent";

	private static final String URL_PREFIX;
	private static final String USER_AGENT;

	static {
		URL_PREFIX = RabbitConfig.getInstance().getApiDomain();
		USER_AGENT = RabbitConfig.getInstance().getUserAgent();
	}

	public static <T> void get(String uri, Class<T> clazz,
			Response.Listener successListener, Response.ErrorListener errorListener) {
		get(uri, clazz, null, successListener, errorListener);
	}

	public static <T> void get(String uri, Class<T> clazz, Map<String, String> params,
			Response.Listener successListener, Response.ErrorListener errorListener) {
		executeApi(Request.Method.GET, uri, clazz, params, successListener, errorListener);
	}

	public static <T> void post(String uri, Class<T> clazz,
			Response.Listener successListener, Response.ErrorListener errorListener) {
		post(uri, clazz, null, successListener, errorListener);
	}

	public static <T> void post(String uri, Class<T> clazz, Map<String, String> params,
			Response.Listener successListener, Response.ErrorListener errorListener) {
		executeApi(Request.Method.POST, uri, clazz, params, successListener, errorListener);
	}

	private static <T> void executeApi(int method, String uri, Class<T> clazz, Map<String, String> params,
			Response.Listener successListener, Response.ErrorListener errorListener) {
		String url = buildUrl(method, uri, params);
		Map<String, String> headers = buildHeaders();
		Request request = createRequest(method, url, clazz, params, headers, successListener, errorListener);
		RabbitVolley.getRequestQueue().add(request);
	}

	private static Map<String, String> buildHeaders() {
		Map<String, String> headers = new HashMap<>();
		headers.put(KEY_AGENT, USER_AGENT);
		return headers;
	}

	public static String buildUrl(int method, String uri, Map<String, String> params) {
		String url;
		if (StringUtils.isBlank(uri)){
			url = URL_PREFIX;
		}else {
			Uri parseUri = Uri.parse(uri);
			String scheme = parseUri.getScheme();
			if (StringUtils.isBlank(scheme)){
				url = URL_PREFIX + uri;
			}else {
				url = uri;
			}

		}
		switch (method) {
			case Request.Method.GET:
			case Request.Method.DELETE:
				if (params != null && !params.isEmpty()) {
					Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
					for (Map.Entry<String, String> entry : params.entrySet()) {
						uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
					}
					return uriBuilder.build().toString();
				} else {
					return url;
				}
			case Request.Method.POST:
			case Request.Method.PUT:
			default:
				return url;
		}
	}

	private static <T> Request createRequest(int method, String url, Class<T> clazz, Map<String, String> params,
			Map<String, String> headers,
			Response.Listener successListener, Response.ErrorListener errorListener) {
		if (String.class.equals(clazz)) {
			return new RabbitStringRequest(method, url, params, headers, successListener, errorListener);
		} else {
			return new RabbitGsonRequest(method, url, clazz, params, headers, successListener, errorListener);
		}

	}

}
