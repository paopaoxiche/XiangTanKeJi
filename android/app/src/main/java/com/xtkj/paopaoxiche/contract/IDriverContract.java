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
}
