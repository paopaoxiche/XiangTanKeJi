package com.xtkj.paopaoxiche.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;

import com.xtkj.paopaoxiche.R;

import java.util.logging.Logger;

public abstract class BaseDialog extends Dialog {
    private static final String TAG = "BaseDialog";
    protected Context context;

    public BaseDialog(Context context) {
        this(context, R.style.dialog_global_style);
    }

    public BaseDialog(Context context, int themeId) {
        super(context, themeId);
        this.context = context;
    }

    @Override
    public void dismiss() {

    }

    protected abstract void initViews();

    protected abstract void initValues();

    protected abstract void initListener();

}
