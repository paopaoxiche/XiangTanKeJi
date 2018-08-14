package com.xtkj.paopaoxiche.view.view;

import android.content.Context;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

public class MyCouponsDialog extends FullScreenWithStatusBarDialog {
    public MyCouponsDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_my_coupons);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }
}
