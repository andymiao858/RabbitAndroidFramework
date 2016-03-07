package com.rabbit.framework.network.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.RabbitRequest;
import com.android.volley.toolbox.WritableBody;
import com.rabbit.framework.models.bo.UploadBo;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author miaohd
 */
public class UploadRequest extends RabbitRequest<UploadBo> implements WritableBody{

	private UploadBo uploadBo;

	private Map<String, String> parameters;
	private Map<String, String> headers;
	private HttpEntity multipartEntity;

	public UploadRequest(UploadBo uploadBo, Response.Listener successListener, Response.ErrorListener errorListener) {
		super(Method.POST, uploadBo.getUrl(), successListener, errorListener);
		this.uploadBo = uploadBo;
		parameters = buildParams();
		headers = buildHeaders();
		multipartEntity = buildEntity();
	}

	@Override
	protected Response<UploadBo> parseNetworkResponse(NetworkResponse response) {
		return Response.success(uploadBo, HttpHeaderParser.parseCacheHeaders(response));
	}

	@Override
	public String getBodyContentType() {
		return multipartEntity.getContentType().getValue();
	}

	private Map<String, String> buildHeaders() {
		return new HashMap<>();
	}

	private Map<String, String> buildParams() {
		return new HashMap<>();
	}

	private HttpEntity buildEntity() {
		MultipartEntityBuilder builder = MultipartEntityBuilder.create().
				addPart(UploadBo.FILE_KEY, new FileBody(uploadBo.getFile())).
				setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			builder.addTextBody(entry.getKey(), entry.getValue(), ContentType.create("text/plain", Consts.UTF_8));
		}
		return builder.build();
	}


	@Override
	public File getFile() {
		return uploadBo.getFile();
	}
}
