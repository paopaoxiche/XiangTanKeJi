package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.view.CarWashMain.CarWashMineFragment;

public class CarWashMinePresenterImpl implements ICarWashContract.IMinePresenter {

    ICarWashContract.IMineView mineView;

    public CarWashMinePresenterImpl(ICarWashContract.IMineView iMineView) {
        mineView = iMineView;
        mineView.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }
}
