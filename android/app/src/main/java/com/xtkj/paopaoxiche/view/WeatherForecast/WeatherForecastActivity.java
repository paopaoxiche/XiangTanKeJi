package com.xtkj.paopaoxiche.view.WeatherForecast;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseGaodeActivity;
import com.xtkj.paopaoxiche.contract.IWeatherForecastContract;
import com.xtkj.paopaoxiche.presenter.WeatherForecastPresenterImpl;

public class WeatherForecastActivity extends BaseGaodeActivity implements IWeatherForecastContract.IWeatherForecastView{

    IWeatherForecastContract.IWeatherForecastPresenter weatherForecastPresenterlmpl = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initValues();
        initListeners();

        new WeatherForecastPresenterImpl(this);
        weatherForecastPresenterlmpl.onCreate();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void setPresenter(IWeatherForecastContract.IWeatherForecastPresenter iWeatherForecastPresenter) {
        weatherForecastPresenterlmpl = iWeatherForecastPresenter;
    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
