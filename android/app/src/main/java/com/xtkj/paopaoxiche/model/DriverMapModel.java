package com.xtkj.paopaoxiche.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.ArrayRes;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xtkj.paopaoxiche.application.MyLocation;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverMapModel implements AMapLocationListener {


    private ArrayList<DriverMapListener> driverMapListeners  = new ArrayList<>();
    private static DriverMapModel instance ;
    private WashServicesBean washServicesBean = new WashServicesBean();

    DriverMapModel(){
        washServicesBean.setData(new ArrayList<>());
    }

    public void updateLocation(Context context){


    }

    public void addListener(DriverMapListener driverMapListener) {
        driverMapListeners.add(driverMapListener);
    }

    public void removeListener(DriverMapListener driverMapListener) {
        driverMapListeners.remove(driverMapListener);
    }

    public static DriverMapModel getInstance() {
        if (instance == null) {
            instance = new DriverMapModel();
        }
        return instance;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    public interface DriverMapListener{
        void getWashServicesSuccess();
    }



    public void updateLocation(double j ,double w){

        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(WashService.class)
                .getNearbyWashServiceList(j,w ,10,1,9999)
                .enqueue(new Callback<WashServicesBean>() {
                    @Override
                    public void onResponse(Call<WashServicesBean> call, Response<WashServicesBean> response) {
                        washServicesBean.getData().clear();
                        for(WashServicesBean.DataBean dataBean:response.body().getData()){
                            washServicesBean.getData().add(dataBean);
                        }
                        for (DriverMapListener driverMapListeners : driverMapListeners) {
                            driverMapListeners.getWashServicesSuccess();
                        }
                    }

                    @Override
                    public void onFailure(Call<WashServicesBean> call, Throwable t) {

                    }
                });

    }

    public WashServicesBean getWashServicesBean() {
        return washServicesBean;
    }
}
