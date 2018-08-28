package com.xtkj.paopaoxiche.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.xtkj.paopaoxiche.R;

public abstract class FullScreenWithStatusBarDialog extends Dialog {

    public BackButtonClickListener backButtonClickListener = new BackButtonClickListener();

    /**
     * 构造器
     * */
    public FullScreenWithStatusBarDialog(Context context, boolean statusBarVisible) {
        this(context, statusBarVisible
                ? R.style.dialog_full_screen_with_status_bar_style
                : R.style.dialog_full_screen_style);
    }

    public FullScreenWithStatusBarDialog(Context context, int themeId) {
        super(context, themeId);
    }

    @Override
    public void show() {
        setFullScreen();
        setWindowAnimations();
        super.show();
    }

    private void setFullScreen() {
        Window win = getWindow();
        if (win == null) {
            return;
        }
        win.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams lp = win.getAttributes();

        lp.width = LayoutParams.MATCH_PARENT;
        lp.height = LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);
    }

    private void setWindowAnimations() {
        if (getWindow() != null) {
            getWindow().setWindowAnimations(R.style.dialog_right_enter_left_exit_animation);
        }
    }

    class BackButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.back_arrow_image_button) {
                dismiss();
            }
        }
    }

    private AlertDialog loadingDialog;
    public void showLoadingDialog() {
        loadingDialog = new AlertDialog.Builder(getContext()).create();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        loadingDialog.setCancelable(false);
        loadingDialog.setOnKeyListener((dialog, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                return true;
            return false;
        });
        loadingDialog.setCancelable(false);
        loadingDialog.setContentView(R.layout.dialog_loading);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (null != loadingDialog && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
