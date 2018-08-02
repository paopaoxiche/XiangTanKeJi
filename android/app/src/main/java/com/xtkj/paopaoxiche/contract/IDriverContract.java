package com.xtkj.paopaoxiche.contract;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;
import com.xtkj.paopaoxiche.bean.WeatherLiveBean;

public class IDriverContract {

    public interface IDriverPresenter extends IBasePresenter {

    }

    public interface IDriverView extends IBaseView<IDriverPresenter> {
        void setWeather(WeatherLiveBean weatherLiveBean);
    }

    public interface IHomePresenter extends IBasePresenter {

    }

    public interface IHomeView extends IBaseView<IHomePresenter> {

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
