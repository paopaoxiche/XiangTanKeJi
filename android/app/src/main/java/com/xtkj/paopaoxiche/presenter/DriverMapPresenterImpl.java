package com.xtkj.paopaoxiche.presenter;

import android.location.Location;

import com.amap.api.location.AMapLocation;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.contract.IDriverMapContract;
import com.xtkj.paopaoxiche.model.DriverMapModel;
import com.xtkj.paopaoxiche.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class DriverMapPresenterImpl implements IDriverMapContract.IDriverMapPresenter,DriverMapModel.DriverMapListener {

    IDriverMapContract.IDriverMapView driverMapView ;
    private DriverMapModel viewModel;

    public DriverMapPresenterImpl(IDriverMapContract.IDriverMapView iDriverMapView) {
        driverMapView = iDriverMapView;
        driverMapView.setPresenter(this);

        viewModel = DriverMapModel.getInstance();
    }

    @Override
    public void onCreate() {
        viewModel.addListener(this);
    }

    @Override
    public void onDestroy() {
        viewModel.removeListener(this);
    }


    @Override
    public void updateLocation(double j , double w) {
        viewModel.updateLocation(j,w);
    }

    @Override
    public void checkWuYuanXiChe() {
        List<WashServicesBean.DataBean> dataBeanList = DriverMapModel.getInstance().getWashServicesBean().getData();
        if (!UserInfo.isIsCheckWuYuanXiChe()) {
            driverMapView.upDateService(dataBeanList);
            return;
        }
        List<WashServicesBean.DataBean> resultList = new ArrayList<>();
        for (WashServicesBean.DataBean dataBean : dataBeanList) {
            if (dataBean.getPrice() > 5) {
                continue;
            }
            resultList.add(dataBean);
        }
        driverMapView.upDateService(resultList);
    }

    @Override
    public void getWashServicesSuccess() {
        checkWuYuanXiChe();
    }
}
