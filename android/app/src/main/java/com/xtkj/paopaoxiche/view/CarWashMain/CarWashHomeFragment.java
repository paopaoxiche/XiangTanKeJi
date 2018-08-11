package com.xtkj.paopaoxiche.view.CarWashMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.contract.ICarWashContract;

public class CarWashHomeFragment extends BaseFragmemt implements ICarWashContract.IInfoView{

    ICarWashContract.IInfoPresenter infoPresenter;

    Button moreButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View carWashView = inflater.inflate(R.layout.fragment_car_wash_home, null);

        moreButton = carWashView.findViewById(R.id.more_button);

        return carWashView;
    }

    @Override
    public void setPresenter(ICarWashContract.IInfoPresenter iInfoPresenter) {
        infoPresenter = iInfoPresenter;
    }
}
