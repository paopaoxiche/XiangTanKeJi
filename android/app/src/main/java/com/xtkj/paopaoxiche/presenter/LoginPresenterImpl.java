package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.ILoginContract;

public class LoginPresenterImpl implements ILoginContract.ILoginPresenter {

    ILoginContract.ILoginView loginView;

    public LoginPresenterImpl(ILoginContract.ILoginView iLoginView) {
        loginView = iLoginView;
        iLoginView.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
