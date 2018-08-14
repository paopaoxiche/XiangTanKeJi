package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.IWashServiceContract;

public class WashServicePresenterImpl implements IWashServiceContract.IWashServicePresenter {

    IWashServiceContract.IWashServiceView view ;

    public WashServicePresenterImpl(IWashServiceContract.IWashServiceView iWashServiceView){
        this.view = iWashServiceView ;
        view.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
