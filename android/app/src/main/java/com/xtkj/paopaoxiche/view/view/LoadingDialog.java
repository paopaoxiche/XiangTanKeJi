package com.xtkj.paopaoxiche.view.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.xtkj.paopaoxiche.R;

/**
 * 一句话注释。
 * 详细内容。
 *
 * @author sky on 2018/8/28
 * @since 1.0
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_loading);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setBackgroundDrawable(new ColorDrawable());
        setCancelable(false);
        setOnKeyListener((dialog, keyCode, event) ->
            keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }
}
