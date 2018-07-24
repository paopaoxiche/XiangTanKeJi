package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.ICarWashContract;

public class CarWashInfoPresenterImpl implements ICarWashContract.IInfoPresenter {

    ICarWashContract.IInfoView infoView;

    public CarWashInfoPresenterImpl(ICarWashContract.IInfoView iInfoView) {
        infoView = iInfoView;
        infoView.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
