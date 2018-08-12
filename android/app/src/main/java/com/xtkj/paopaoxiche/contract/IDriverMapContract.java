package com.xtkj.paopaoxiche.contract;

import android.content.Context;
import android.location.Location;

import com.xtkj.paopaoxiche.base.IBasePresenter;
import com.xtkj.paopaoxiche.base.IBaseView;

public class IDriverMapContract {

    public interface IDriverMapPresenter extends IBasePresenter {
        void updateLocation(double j, double w);

    }

    public interface IDriverMapView extends IBaseView<IDriverMapPresenter> {
        Context getContext();
        void upDateService();
        void startNavigation(double j, double w);
    }
}
