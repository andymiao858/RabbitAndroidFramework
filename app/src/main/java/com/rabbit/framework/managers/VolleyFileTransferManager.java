package com.rabbit.framework.managers;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.RabbitVolley;
import com.rabbit.framework.models.bo.DownloadBo;
import com.rabbit.framework.network.volley.DownloadRequest;
import com.rabbit.framework.utils.Validator;

/**
 * @author miaohd
 */
public class VolleyFileTransferManager {

	/** The default socket timeout in milliseconds */
	public static final int DEFAULT_TIMEOUT_MS = 10000;

	/** The default number of retries */
	public static final int DEFAULT_MAX_RETRIES = 0;

	/** The default backoff multiplier */
	public static final float DEFAULT_BACKOFF_MULT = 1f;

	private VolleyFileTransferManager() {
	}

	public static VolleyFileTransferManager getInstance() {
		return VolleyFileTransferManagerHolder._instance;
	}

	private static class VolleyFileTransferManagerHolder {
		private static VolleyFileTransferManager _instance = new VolleyFileTransferManager();
	}

	public void newDownloadRequest(DownloadBo downloadBo, Response.Listener successListener,
			Response.ErrorListener errorListener) {
		if (Validator.isEmpty(downloadBo) || Validator.isEmpty(downloadBo.getUrl())
			|| Validator.isEmpty(downloadBo.getDirectory()) || Validator.isEmpty(downloadBo.getFileName())) {
			throw new NullPointerException("DownloadBo and it's parameters(url, directory, filename) can not be null");
		}
		DownloadRequest downloadRequest = new DownloadRequest(downloadBo, successListener, errorListener);
		downloadRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT));
		RabbitVolley.getFileRequestQueue().add(downloadRequest);
	}

}
