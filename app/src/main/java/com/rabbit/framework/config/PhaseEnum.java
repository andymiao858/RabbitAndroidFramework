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

import com.rabbit.framework.BuildConfig;

/**
 * @author miaohd
 */
public enum PhaseEnum {

	ALPHA(PhaseEnum.ALPHA_API_DOMAIN),
	BETA(PhaseEnum.BETA_API_DOMAIN),
	RELEASE(PhaseEnum.RELEASE_API_DOMAIN);

	public static final String ALPHA_API_DOMAIN = "http://www.baidu.com";
	// just show an example how to use apiVersion var
	public static final String BETA_API_DOMAIN = "http://www.baidu.com/" + BuildConfig.API_VERSION + "/";
	public static final String RELEASE_API_DOMAIN = "http://www.baidu.com";

	private final String apiDomain;

	PhaseEnum(String apiDomain){
		this.apiDomain = apiDomain;
	}

	public String getApiDomain() {
		return apiDomain;
	}
}
