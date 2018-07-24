package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.ICarWashContract;

public class CarWashMainPresenterImpl implements ICarWashContract.IMainPresenter {

    ICarWashContract.IMainView mainView;

    public CarWashMainPresenterImpl(ICarWashContract.IMainView iMainView) {
        mainView = iMainView;
        mainView.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
