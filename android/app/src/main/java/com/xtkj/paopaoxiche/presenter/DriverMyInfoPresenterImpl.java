package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.IDriverContract;

public class DriverMyInfoPresenterImpl implements IDriverContract.IMyInfoPresenter{

    IDriverContract.IMyInfoView myInfoView;

    public DriverMyInfoPresenterImpl(IDriverContract.IMyInfoView iMyInfoView) {
        myInfoView = iMyInfoView;
        myInfoView.setPresenter(this);
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
