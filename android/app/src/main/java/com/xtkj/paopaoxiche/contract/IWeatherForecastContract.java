package com.xtkj.paopaoxiche.contract;

import android.content.Context;
import android.content.Intent;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;

public class IWeatherForecastContract {

    public interface IWeatherForecastPresenter extends IBasePresenter {

    }

    public interface IWeatherForecastView extends IBaseView<IWeatherForecastPresenter> {
        void setAddress(String address);
        void setRealTimeWeather(WeatherRealTimeBean weatherRealTimeBean);
        void setForecastWeather(WeatherForecastBean weatherForecastBean);



        Context getActivityContext();
    }
}
