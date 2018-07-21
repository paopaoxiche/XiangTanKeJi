package com.xtkj.paopaoxiche.presenter;

import android.util.Log;

import com.xtkj.paopaoxiche.bean.WeatherLiveBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WeatherService;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverPresenterImpl implements IDriverContract.IDriverPresenter {

    IDriverContract.IDriverView driverView;

    public DriverPresenterImpl(IDriverContract.IDriverView iDriverView) {
        driverView = iDriverView;
        iDriverView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(WeatherService.class)
                .getWeatherLive("101280601")
                .enqueue(new Callback<WeatherLiveBean>() {
                    @Override
                    public void onResponse(Call<WeatherLiveBean> call, Response<WeatherLiveBean> response) {
                        driverView.setWeather(response.body());
                    }

                    @Override
                    public void onFailure(Call<WeatherLiveBean> call, Throwable t) {
                        Log.e("call",call.toString());
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
