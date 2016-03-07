package com.rabbit.framework.managers;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.util.LongSparseArray;

import com.rabbit.framework.ApplicationKeeper;
import com.rabbit.framework.models.bo.DownloadBo;
import com.rabbit.framework.utils.RLog;
import com.rabbit.framework.utils.Validator;

import java.io.File;

/**
 * @author miaohd
 */
public class RabbitDownloadManager {

	private static final String TAG = "RabbitDownloadManager";

	private final Context context;

	private LongSparseArray<DownloadCompleteListener> downloadListners = new LongSparseArray<>();

	private LongSparseArray<DownloadBo> downloadBos = new LongSparseArray<>();

	private DownloadManager downloadManager;

	public interface DownloadCompleteListener{
		public void onSuccess(DownloadBo downloadBo);
		public void onFail(DownloadBo downloadBo);
	}

	private RabbitDownloadManager(){
		this.context = ApplicationKeeper.getApplicationContext();
		this.downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
		context.registerReceiver(downloadReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
	}

	public static RabbitDownloadManager getInstance(){
		return DownloadManagerHolder._instance;
	}

	private static class DownloadManagerHolder {
		private static final RabbitDownloadManager _instance = new RabbitDownloadManager();
	}

	public long newDownloadRequest(DownloadBo downloadBo, DownloadCompleteListener listener){
		return newDownloadRequest(downloadBo, listener, null, null);
	}

	public long newDownloadRequest(DownloadBo downloadBo, DownloadCompleteListener listener, String notiTitle, String notiDesc){
		if (Validator.isEmpty(downloadBo.getDirectory()) || Validator.isEmpty(downloadBo.getFileName()) || Validator.isEmpty(downloadBo.getUrl())){
			RLog.w(TAG, "DownloadBo or DownloadBo parameters is null. So we dismiss the request");
			return -1;
		}
		DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadBo.getUrl()));
		if (Validator.isNotEmpty(notiTitle) || Validator.isNotEmpty(notiDesc)){
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		}
		request.setTitle(notiTitle);
		request.setDescription(notiDesc);
		File file = new File(downloadBo.getDirectory());
		if (!file.exists()){
			file.mkdir();
		}
		request.setDestinationInExternalPublicDir(downloadBo.getDirectory(), downloadBo.getFileName());
		long downloadId = downloadManager.enqueue(request);
		downloadBos.put(downloadId, downloadBo);
		if (listener != null){
			downloadListners.put(downloadId, listener);
		}
		return downloadId;
	}

	public void cancelDownload(long downloadRef){
		downloadManager.remove(downloadRef);
		downloadListners.remove(downloadRef);
		downloadBos.remove(downloadRef);
	}


	public BroadcastReceiver downloadReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
				long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
				DownloadCompleteListener listener = downloadListners.get(downloadId);
				DownloadBo downloadBo = downloadBos.get(downloadId);
				if (listener != null){
					DownloadManager.Query query = new DownloadManager.Query();
					query.setFilterById(downloadId);
					Cursor cursor = downloadManager.query(query);
					if (cursor.moveToFirst()){
						int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
						if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)){
							listener.onSuccess(downloadBo);
						} else if (DownloadManager.STATUS_FAILED == cursor.getInt(columnIndex)){
							listener.onFail(downloadBo);
						}
					}
					cursor.close();
					downloadListners.remove(downloadId);
					downloadBos.remove(downloadId);
				}

			}
		}
	};


	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (context != null) {
			context.unregisterReceiver(downloadReceiver);
		}
	}

}
