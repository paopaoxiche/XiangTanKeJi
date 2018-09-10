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

    int checkedWashId = -1;

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
        List<WashServicesBean.DataBean> resultList = new ArrayList<>();
        if (!UserInfo.isIsCheckWuYuanXiChe()) {
            resultList = dataBeanList;
        } else {

            for (WashServicesBean.DataBean dataBean : dataBeanList) {
                if (dataBean.getPrice() > 5) {
                    continue;
                }
                resultList.add(dataBean);
            }
        }
        int position = 0;
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getWashId() == checkedWashId) {
                position = i;
                break;
            }
        }
        if (position > 0) {
            resultList.add(0, resultList.get(position));
            resultList.remove(position + 1);
        }
        driverMapView.upDateService(resultList);
    }

    @Override
    public void checkedWash(int washId) {
        checkedWashId = washId;
    }

    @Override
    public void getWashServicesSuccess() {
        checkWuYuanXiChe();
    }
}
