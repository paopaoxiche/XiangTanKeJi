package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.DemoBean;
import com.xtkj.paopaoxiche.bean.WeatherLiveBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {

    /**
     *  @param city
     * */
    @GET("weather/live")
    Call<WeatherLiveBean> getWeatherLive(@Query("city") String city) ;


}
