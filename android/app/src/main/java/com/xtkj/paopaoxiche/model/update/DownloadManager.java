package com.xtkj.paopaoxiche.model.update;

import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import com.xtkj.paopaoxiche.application.BaseApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadManager {
    private static final String TAG = "DownloadManager";

    private static AsyncDownload asyncDownload;

    /**
     * 下载APK
     *
     * @param url          APK链接
     * @param callback     回调
     */
    public static void downloadAPK(String url, DownloadAPKCallback callback) {
        if (TextUtils.isEmpty(url) || callback == null) {
            return;
        }
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(BaseApplication.getContext(), "没有sdcard存储空间，无法下载", Toast.LENGTH_LONG).show();
            return;
        }

        File downloadFile = new File(Environment.getExternalStorageDirectory().getPath(), "paopaoxiche.apk");
        if (downloadFile.exists()) {
            downloadFile.delete();
        }

        asyncDownload = new AsyncDownload(url, downloadFile, callback);
        asyncDownload.execute();
    }

    /**
     * 取消下载
     */
    public static void cancelDown() {
        if (null == asyncDownload) {
            return;
        }
        asyncDownload.cancelCall();
        asyncDownload.cancel(true);
    }

    /**
     * 下载
     *
     * @author Money
     */
    private static class AsyncDownload extends AsyncTask<Void, Long, Boolean> {
        private final String url;
        private final DownloadAPKCallback callback;
        private final File file;
        private Call call;

        public AsyncDownload(String url, File downloadFile, DownloadAPKCallback callback) {
            this.url = url;
            this.file = downloadFile;
            this.callback = callback;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(2, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            OkHttpClient client = builder.build();
            Request.Builder requestBuilder = new Request.Builder();
            InputStream inputStream = null;
            FileOutputStream fileOutputStream = null;
            Response response = null;
            try {
                requestBuilder.url(url);
                Request request = requestBuilder.build();
                call = client.newCall(request);
                response = call.execute();
                if (response.code() == 200) {
                    inputStream = response.body().byteStream();
                    byte[] buff = new byte[1024 * 2];
                    int len = 0;
                    long total = response.body().contentLength();
                    long download = 0;
                    publishProgress(0L, total);
                    fileOutputStream = new FileOutputStream(file);
                    while ((len = inputStream.read(buff)) != -1) {
                        if (call.isCanceled()) {
                            return false;
                        }

                        download += len;
                        fileOutputStream.write(buff, 0, len);
                        publishProgress(download, total);
                    }

                } else {
                    return false;
                }
            } catch (Exception exception) {
                return false;
            } finally {
                if (null != response) {
                    response.body().close();
                }

                if (null != call && !call.isCanceled()) {
                    call.cancel();
                }

                if (null != inputStream) {
                    try {
                        inputStream.close();
                    } catch (IOException exception) {
                    }
                }

                if (null != fileOutputStream) {
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException exception) {
                    }
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            callback.progress(values[0], values[1]);
        }

        @Override
        protected void onPostExecute(Boolean isSuccess) {
                callback.result(isSuccess, file);
        }

        public void cancelCall() {
            if (null != call) {
                call.cancel();
            }
        }
    }
}