package com.xtkj.paopaoxiche.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initViews();

    protected abstract void initValues();

    protected abstract void initListeners();
}
