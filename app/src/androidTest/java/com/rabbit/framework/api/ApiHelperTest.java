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

import com.android.volley.Request;
import com.rabbit.framework.config.RabbitConfig;
import com.rabbit.framework.utils.RLog;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author miaohd
 */
public class ApiHelperTest {

	@Test
	public void testBuildUrl(){
		String url = ApiHelper.buildUrl(Request.Method.GET, "/s", null);
		RLog.d(url);
		Assert.assertEquals(url, RabbitConfig.getInstance().getApiDomain() + "/s");
		url = ApiHelper.buildUrl(Request.Method.GET, "http://www.sina.com/", null);
		RLog.d(url);
		Assert.assertEquals(url, "http://www.sina.com");

	}

}
