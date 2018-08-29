package com.xtkj.paopaoxiche.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.model.DriverHomeModel;

public class RegisterWashActivity extends AppCompatActivity implements DriverHomeModel.DriverHomeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_wash);

        DriverHomeModel.getInstance().initLocation(this);
    }

    @Override
    public void getLocationSuccess(String address) {

    }

    @Override
    public void getWashServicesSuccess(WashServicesBean washServicesBean) {

    }

    @Override
    public void getWashServicesFail() {

    }

    @Override
    public void getRealTimeWeatherSuccess(WeatherRealTimeBean weatherRealTimeBean) {

    }

    @Override
    public void getRealTimeWeatherFailed() {

    }

    @Override
    public void getForecastWeatherSuccess(WeatherForecastBean weatherForecastBean) {

    }

    @Override
    public void getForecastWeatherFailed() {

    }

    @Override
    public void getCommoditySuccess(WashShopBean washShopBean) {

    }

    @Override
    public void getCommodityFailed() {

    }
}
