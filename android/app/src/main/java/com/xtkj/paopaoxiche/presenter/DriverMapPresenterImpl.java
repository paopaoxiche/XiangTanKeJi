package com.xtkj.paopaoxiche.presenter;

import android.location.Location;

import com.amap.api.location.AMapLocation;
import com.xtkj.paopaoxiche.contract.IDriverMapContract;
import com.xtkj.paopaoxiche.model.DriverMapModel;

public class DriverMapPresenterImpl implements IDriverMapContract.IDriverMapPresenter,DriverMapModel.DriverMapListener {

    IDriverMapContract.IDriverMapView driverMapView ;
    private DriverMapModel viewModel;

    public DriverMapPresenterImpl(IDriverMapContract.IDriverMapView iDriverMapView) {
        driverMapView = iDriverMapView;
        driverMapView.setPresenter(this);

        viewModel = DriverMapModel.getInstance();
        viewModel.addListener(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }


    @Override
    public void updateLocation(double j , double w) {
        viewModel.updateLocation(j,w);
    }

    @Override
    public void getWashServicesSuccess() {
        driverMapView.upDateService();
    }
}
