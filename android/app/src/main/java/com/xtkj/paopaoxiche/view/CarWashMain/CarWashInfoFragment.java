package com.xtkj.paopaoxiche.view.CarWashMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.contract.ICarWashContract;

public class CarWashInfoFragment extends BaseFragmemt implements ICarWashContract.IInfoView{

    ICarWashContract.IInfoPresenter infoPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View carWashView = inflater.inflate(R.layout.fragment_car_wash_main, null);
        return carWashView;
    }

    @Override
    public void setPresenter(ICarWashContract.IInfoPresenter iInfoPresenter) {
        infoPresenter = iInfoPresenter;
    }
}
