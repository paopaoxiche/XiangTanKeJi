package com.xtkj.paopaoxiche.model.update;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;

import java.io.File;

public class DownloadAPKCallback {

    private boolean forceUpdate;
    private Dialog downloadDialog;
    private ProgressBar pbDownload;
    private Button btnCancelDown;
    private TextView tvProgress;
    private TextView tvDownTip;
    private Activity activity;

    DismissCallback dismissCallback = null;

    /**
     * 构造函数
     */
    public DownloadAPKCallback(Activity activity, boolean mustUpdate) {
        this.activity = activity;
        forceUpdate = mustUpdate;
        downloadDialog = new Dialog(activity, R.style.inputRoomPasswordDialog);
        View downloadDialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_download, null);
        downloadDialog.setContentView(downloadDialogView);
        pbDownload = (ProgressBar) downloadDialogView.findViewById(R.id.pb_download);
        btnCancelDown = (Button) downloadDialogView.findViewById(R.id.btn_cancel_download);
        tvProgress = (TextView) downloadDialogView.findViewById(R.id.tv_progress);
        tvDownTip = (TextView) downloadDialogView.findViewById(R.id.tv_down_tip);
        btnCancelDown.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.cancelDown();
                downloadDialog.dismiss();
                // 强制更新，关闭依赖的activity
                if (forceUpdate) {
                    if (dismissCallback != null) {
                        dismissCallback.canContinueAfterDismiss(false);
                    }
                } else {
                    if (dismissCallback != null) {
                        dismissCallback.canContinueAfterDismiss(true);
                    }
                }
            }
        });
        downloadDialog.setCancelable(false);
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.show();
    }

    public void setDismissCallback(DismissCallback dis) {
        dismissCallback = dis;
    }

    public interface DismissCallback {
        void canContinueAfterDismiss(boolean canContinue);
    }

    /**
     * 进度
     */
    public void progress(long download, long total) {
        pbDownload.setMax((int) total);
        pbDownload.setProgress((int) download);

        float fd = (float) download / 1024 / 1024;
        float ft = (float) total / 1024 / 1024;

        tvProgress.setText("(" + String.format("%.1f", fd) + "M/" + String.format("%.1f", ft) + "M)");
    }

    /**
     * 结果
     */
    public void result(boolean isSuccess, File file) {
        if (isSuccess) {
            downloadDialog.dismiss();
            startInstallAPK(file);
        } else {
            tvDownTip.setVisibility(View.GONE);
            tvProgress.setTextColor(Color.RED);
            tvProgress.setText("下载失败");
        }
    }

    /**
     * 调用系统安装器开始安装
     *
     * @param file 下载完成的APK
     */
    private void startInstallAPK(File file) {
        if (file == null) {
            return;
        }

        if (dismissCallback != null) {
            dismissCallback.canContinueAfterDismiss(false);
        }

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // "net.csdn.blog.ruancoder.fileprovider"即是在清单文件中配置的authorities
                data = FileProvider.getUriForFile(activity, "net.csdn.blog.ruancoder.fileprovider", file);
                // 给目标应用一个临时授权
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                data = Uri.fromFile(file);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(data, "application/vnd.android.package-archive");
            activity.startActivity(intent);
        } catch (ActivityNotFoundException exception) {
        }
    }
}