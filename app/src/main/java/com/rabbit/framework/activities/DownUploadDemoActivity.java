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

import android.os.Bundle;
import android.widget.Button;

import com.rabbit.framework.R;
import com.rabbit.framework.managers.RabbitDownloadManager;
import com.rabbit.framework.models.bo.DownloadBo;
import com.rabbit.framework.utils.RLog;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author miaohd
 */
public class DownUploadDemoActivity extends BaseActivity {

	@Bind(R.id.download_btn)
	Button downloadBtn;

	@OnClick(R.id.download_btn)
	void onClickDownloadBtn() {
		DownloadBo downloadBo = new DownloadBo("http://www.ti.com/lit/ds/symlink/adc0804-n.pdf", "/RabbitAndroidFramework", "doc-"
			+ new Random().nextInt(100) + ".pdf");
		RabbitDownloadManager.getInstance().newDownloadRequest(downloadBo, downloadCompleteListener, "adc0804", "datasheet");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downupload);
		ButterKnife.bind(this);
	}

	private RabbitDownloadManager.DownloadCompleteListener downloadCompleteListener = new RabbitDownloadManager.DownloadCompleteListener() {
		@Override
		public void onSuccess() {
			RLog.d("Download Success");
		}

		@Override
		public void onFail() {
			RLog.e("Download Fail");
		}
	};

}
