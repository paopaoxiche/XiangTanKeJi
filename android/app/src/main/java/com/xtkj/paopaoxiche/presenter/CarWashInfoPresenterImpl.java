package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.model.DriverHomeModel;

public class CarWashInfoPresenterImpl implements ICarWashContract.IInfoPresenter, DriverHomeModel.DriverHomeListener {

    ICarWashContract.IInfoView infoView;
    DriverHomeModel driverHomeModel;

    public CarWashInfoPresenterImpl(ICarWashContract.IInfoView iInfoView) {
        infoView = iInfoView;
        infoView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        driverHomeModel = DriverHomeModel.getInstance();
        initLocation();
        driverHomeModel.addListener(this);
    }

    @Override
    public void onDestroy() {
        DriverHomeModel.getInstance().removeListener(this);
        DriverHomeModel.release();
    }

    private void initLocation(){
        driverHomeModel.initLocation(infoView.getActivityContext());
    }


    @Override
    public void getLocationSuccess(String address) {
        infoView.setAddress(address);
    }

    @Override
    public void getWashServicesSuccess(WashServicesBean washServicesBean) {

    }

    @Override
    public void getWashServicesFail() {

    }

    @Override
    public void getRealTimeWeatherSuccess(WeatherRealTimeBean weatherRealTimeBean) {
        infoView.setRealTimeWeather(weatherRealTimeBean);
    }

    @Override
    public void getRealTimeWeatherFailed() {

    }

    @Override
    public void getForecastWeatherSuccess(WeatherForecastBean weatherForecastBean) {
        infoView.setForecastWeather(weatherForecastBean);
    }

    @Override
    public void getForecastWeatherFailed() {

    }
}
