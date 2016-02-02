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

import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author miaohd
 */
public class SearchApi{

	public static <T> void search(String searchwords, Class<T> responseClass,
								  Response.Listener<T> successListener, Response.ErrorListener errorListener){
		String uri = "/s";
		Map<String, String> params = new HashMap<>();
		params.put("wd", searchwords);
		ApiHelper.<T>get(uri, responseClass, params, successListener, errorListener);
	}

}
