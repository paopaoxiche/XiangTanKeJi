package com.xtkj.paopaoxiche.view.view;

import android.content.Context;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

public class FeedbackDialog extends FullScreenWithStatusBarDialog{


    public FeedbackDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_feedback);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }
}
