package com.xtkj.paopaoxiche.presenter;

import android.content.Intent;
import android.os.Handler;

import com.xtkj.paopaoxiche.activity.LoginActivity;
import com.xtkj.paopaoxiche.contract.IGuideContract;

public class GuidePresenterImpl implements IGuideContract.IGuidePresenter {

    IGuideContract.IGuideView guideView;

    public GuidePresenterImpl(IGuideContract.IGuideView iGuideView) {
        guideView = iGuideView;
        guideView.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void autoLogin() {
        // TODO 这里需要http去判断TOKEN是否生效
        Intent intent = new Intent();
        new Handler().postDelayed(() -> guideView.startActivityForIntent(intent, LoginActivity.class), 3000);
    }
}
