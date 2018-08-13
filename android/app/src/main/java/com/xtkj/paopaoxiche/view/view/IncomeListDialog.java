package com.xtkj.paopaoxiche.view.view;

import android.content.Context;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

public class IncomeListDialog extends FullScreenWithStatusBarDialog {
    public IncomeListDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }


}
