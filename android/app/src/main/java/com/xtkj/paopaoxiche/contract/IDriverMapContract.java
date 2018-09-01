package com.xtkj.paopaoxiche.contract;

import android.content.Context;
import android.location.Location;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;
import com.xtkj.paopaoxiche.bean.WashServicesBean;

import java.util.List;

public class IDriverMapContract {

    public interface IDriverMapPresenter extends IBasePresenter {
        void updateLocation(double j, double w);
        void checkWuYuanXiChe();
    }

    public interface IDriverMapView extends IBaseView<IDriverMapPresenter> {
        Context getContext();
        void upDateService(List<WashServicesBean.DataBean> dataBeanList);
        void startNavigation(double j, double w);
    }
}
