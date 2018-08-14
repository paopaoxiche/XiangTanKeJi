package com.xtkj.paopaoxiche.view.view;

import android.content.Context;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

public class MyCustomDialog extends FullScreenWithStatusBarDialog {
    public MyCustomDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_custom);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }
}
