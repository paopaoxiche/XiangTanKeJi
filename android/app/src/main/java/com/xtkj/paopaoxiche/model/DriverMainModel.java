package com.xtkj.paopaoxiche.model;

import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WeatherService;

import rx.Observable;

public class DriverMainModel {

    private static DriverMainModel instance;
    public static DriverMainModel getInstance() {
        if (instance == null) {
            instance = new DriverMainModel();
        }
        return instance;
    }

    public Observable getRealTimeWeather(double j, double w){
        return  RetrofitClient.newInstance(ApiField.getBaseWeatherUrl(j,w))
                .create(WeatherService.class)
                .getRealTime();
    }

    public Observable getForecastWeather(double j, double w){
        return  RetrofitClient.newInstance(ApiField.getBaseWeatherUrl(j,w))
                .create(WeatherService.class)
                .getRorecast();
    }

}
