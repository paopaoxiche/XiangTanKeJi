package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface WeatherService {

    @GET("realtime.json")
    Call<WeatherRealTimeBean> getRealTime() ;


    @GET("forecast.json")
    Call<WeatherForecastBean> getRorecast() ;

}
