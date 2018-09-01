package com.xtkj.paopaoxiche.view.view;

import android.content.Context;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2018/9/1.
 */
class AddCarDialog extends FullScreenWithStatusBarDialog{
    public AddCarDialog(Context context) {
        super(context, true);
        setContentView(R.layout.dialog_add_car);
    }
}
