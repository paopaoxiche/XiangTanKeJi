package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.IDriverContract;

public class DriverHomePresenterImpl implements IDriverContract.IHomePresenter {

    IDriverContract.IHomeView homeView;

    public DriverHomePresenterImpl(IDriverContract.IHomeView iHomeView) {
        homeView = iHomeView;
        homeView.setPresenter(this);
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
