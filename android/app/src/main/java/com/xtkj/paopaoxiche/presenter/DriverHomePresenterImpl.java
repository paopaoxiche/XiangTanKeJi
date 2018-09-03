package com.xtkj.paopaoxiche.presenter;

import android.util.Log;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.model.DriverHomeModel;

public class DriverHomePresenterImpl implements IDriverContract.IHomePresenter,DriverHomeModel.DriverHomeListener {

    private IDriverContract.IHomeView homeView;

    private DriverHomeModel viewModel;

    public DriverHomePresenterImpl(IDriverContract.IHomeView iHomeView) {
        viewModel = DriverHomeModel.getInstance();
        homeView = iHomeView;
        homeView.setPresenter(this);
    }


    @Override
    public void onCreate() {
        viewModel = DriverHomeModel.getInstance();
        initLocation();
        viewModel.addListener(this);
    }

    @Override
    public void onDestroy() {
        DriverHomeModel.getInstance().removeListener(this);
        DriverHomeModel.release();
    }

    private void initLocation(){
        viewModel.initLocation(homeView.getActivityContext());
    }


    @Override
    public void updateLocation() {
        viewModel.updateLocation();
    }


    @Override
    public void getLocationSuccess(String address) {
        homeView.setAddress(address);
    }

    @Override
    public void getWashServicesSuccess(WashServicesBean washServicesBean) {
        homeView.updateWashServicesBean();
    }

    @Override
    public void getWashServicesFail() {

    }

    @Override
    public void getRealTimeWeatherSuccess(WeatherRealTimeBean weatherRealTimeBean) {
        homeView.setRealTimeWeather(weatherRealTimeBean);
    }

    @Override
    public void getRealTimeWeatherFailed() {

    }

    @Override
    public void getForecastWeatherSuccess(WeatherForecastBean weatherForecastBean) {
        homeView.setForecastWeather(weatherForecastBean);
        homeView.updateCommodity();
    }

    @Override
    public void getForecastWeatherFailed() {

    }

    @Override
    public void getCommoditySuccess(WashShopBean washShopBean) {
        homeView.updateCommodity();
    }

    @Override
    public void getCommodityFailed() {

    }
}
