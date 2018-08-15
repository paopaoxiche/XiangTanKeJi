package com.xtkj.paopaoxiche.presenter;

import android.util.Log;

import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.service.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverPresenterImpl implements IDriverContract.IDriverPresenter {

    IDriverContract.IDriverView driverView;
    public DriverPresenterImpl(IDriverContract.IDriverView iDriverView) {
        driverView = iDriverView;
        iDriverView.setPresenter(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
    }
}
