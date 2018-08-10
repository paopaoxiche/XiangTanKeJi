package com.xtkj.paopaoxiche.contract;

import android.content.Context;
import android.content.Intent;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;

public class IWeatherForecastContract {

    public interface IWeatherForecastPresenter extends IBasePresenter {

    }

    public interface IWeatherForecastView extends IBaseView<IWeatherForecastPresenter> {
        void setAddress(String address);
        Context getActivityContext();
    }
}
