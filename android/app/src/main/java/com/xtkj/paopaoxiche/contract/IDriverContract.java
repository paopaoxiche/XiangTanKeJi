package com.xtkj.paopaoxiche.contract;

import android.content.Context;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;

public class IDriverContract {

    public interface IDriverPresenter extends IBasePresenter {

    }

    public interface IDriverView extends IBaseView<IDriverPresenter> {
        void setWeather(WeatherRealTimeBean weatherRealTimeBean);
    }

    public interface IHomePresenter extends IBasePresenter {
        void updateLocation();
    }

    public interface IHomeView extends IBaseView<IHomePresenter> {
        Context getActivityContext();
        void setAddress(String address);
        void setRealTimeWeather(WeatherRealTimeBean weatherRealTimeBean);
        void setForecastWeather(WeatherForecastBean weatherForecastBean);
        void setWashService(WashServicesBean washServicesBean);

    }

    public interface IMyInfoPresenter extends IBasePresenter {

    }

    public interface IMyInfoView extends IBaseView<IMyInfoPresenter> {

    }

    public interface IShopPresenter extends IBasePresenter {

    }

    public interface IShopView extends IBaseView<IShopPresenter> {

    }
}
