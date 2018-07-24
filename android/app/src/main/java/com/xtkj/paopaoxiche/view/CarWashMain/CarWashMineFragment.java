package com.xtkj.paopaoxiche.view.CarWashMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.contract.ICarWashContract;

public class CarWashMineFragment extends BaseFragmemt implements ICarWashContract.IMineView {

    ICarWashContract.IMinePresenter minePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_car_wash_main, null);
        return view;
    }

    @Override
    public void setPresenter(ICarWashContract.IMinePresenter iMinePresenter) {
        minePresenter = iMinePresenter;
    }
}
