package com.rabbit.framework.network.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.RabbitRequest;
import com.rabbit.framework.models.bo.DownloadBo;
import com.rabbit.framework.utils.IOUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author miaohd
 */
public class DownloadRequest extends RabbitRequest<DownloadBo> {

	private DownloadBo downloadBo;

	public DownloadRequest(DownloadBo downloadBo, Response.Listener successListener, Response.ErrorListener errorListener){
		super(Method.GET, downloadBo.getUrl(), successListener, errorListener);
		this.downloadBo = downloadBo;
	}

	@Override
	protected Response<DownloadBo> parseNetworkResponse(NetworkResponse response) {
		byte[] data = response.data;
		File file = new File(downloadBo.getDirectory(), downloadBo.getFileName());

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(data);
		} catch (FileNotFoundException e) {
			logStackTrace(e);
			return Response.error(new VolleyError(String.format("File [%s] is not found.", file.getAbsolutePath())));
		} catch (IOException e) {
			logStackTrace(e);
			return Response.error(new VolleyError(String.format("File [%s] write error.", file.getAbsolutePath())));
		} finally {
			IOUtil.closeIO(fos);
		}
		if (file.exists()){
			return Response.success(downloadBo, HttpHeaderParser.parseCacheHeaders(response));
		} else {
			return Response.error(new VolleyError(response));
		}
	}
}
