package com.xtkj.paopaoxiche.view;

import android.content.Intent;
import android.os.Bundle;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.contract.IGuideContract;
import com.xtkj.paopaoxiche.presenter.GuidePresenterImpl;

public class GuideActivity extends BaseActivity implements IGuideContract.IGuideView{

    IGuideContract.IGuidePresenter guidePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        new GuidePresenterImpl(this);
        guidePresenter.onCreate();

        guidePresenter.autoLogin();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void startActivityForIntent(Intent intent, Class clazz) {
        intent.setClass(this, clazz);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(IGuideContract.IGuidePresenter iGuidePresenter) {
        guidePresenter = iGuidePresenter;
    }
}
