package com.xtkj.paopaoxiche.presenter;

import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xtkj.paopaoxiche.contract.IWeatherForecastContract;
import com.xtkj.paopaoxiche.model.DriverHomeModel;

public class WeatherForecastPresenterImpl implements IWeatherForecastContract.IWeatherForecastPresenter {


    private IWeatherForecastContract.IWeatherForecastView weatherForecastView;

    public WeatherForecastPresenterImpl(IWeatherForecastContract.IWeatherForecastView iWeatherForecastView) {
        weatherForecastView = iWeatherForecastView;
        weatherForecastView.setPresenter(this);
    }
    @Override
    public void onCreate() {
        DriverHomeModel driverHomeModel = DriverHomeModel.getInstance();
        weatherForecastView.setAddress(driverHomeModel.getAddress());
        weatherForecastView.setForecastWeather(driverHomeModel.getWeatherForecastBean());
        weatherForecastView.setRealTimeWeather(driverHomeModel.getWeatherRealTimeBean());
    }



    @Override
    public void onDestroy() {

    }
}
