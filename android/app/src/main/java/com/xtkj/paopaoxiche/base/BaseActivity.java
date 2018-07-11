package com.xtkj.paopaoxiche.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by licx on 2017/1/10.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initViews(View view);

    protected abstract void initValues();

    protected abstract void initListeners();
}
