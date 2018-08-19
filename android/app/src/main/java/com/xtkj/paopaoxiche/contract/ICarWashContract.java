package com.xtkj.paopaoxiche.contract;

import android.content.Context;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;

public class ICarWashContract {

    public interface IMainPresenter extends IBasePresenter {

    }

    public interface IMainView extends IBaseView<IMainPresenter> {

    }

    public interface IInfoPresenter extends IBasePresenter {
        void updateLocation();
    }

    public interface IInfoView extends IBaseView<IInfoPresenter> {
        Context getActivityContext();
        void setAddress(String address);
        void setRealTimeWeather(WeatherRealTimeBean weatherRealTimeBean);
        void setForecastWeather(WeatherForecastBean weatherForecastBean);

        void setGoodsInfoList(WashShopBean washShopBean);
    }

    public interface IMinePresenter extends IBasePresenter {

    }

    public interface IMineView extends IBaseView<IMinePresenter> {

    }
}
