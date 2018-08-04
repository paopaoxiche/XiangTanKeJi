package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.IDriverContract;

public class DriverShopPresenterImpl implements IDriverContract.IShopPresenter{

    IDriverContract.IShopView shopView;

    public DriverShopPresenterImpl(IDriverContract.IShopView iShopView) {
        shopView = iShopView;
        shopView.setPresenter(this);
    }


    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }


}
