package com.xtkj.paopaoxiche.model;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.service.WeatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverHomeModel {

    private static DriverHomeModel instance;
    private List<DriverHomeListener> driverHomeListenerList = null;
    private AMapLocationClient mLocationClient =null;

    private double longitude = 0;
    private double latitude = 0;
    private String address  = "";
    private WeatherRealTimeBean weatherRealTimeBean = null;
    private WeatherForecastBean weatherForecastBean = null;

    private DriverHomeModel(){
        driverHomeListenerList = new ArrayList<>();
    }

    public void addListener(DriverHomeListener driverHomeListener) {
        driverHomeListenerList.add(driverHomeListener);
    }

    public void removeListener(DriverHomeListener driverHomeListener) {
        driverHomeListenerList.remove(driverHomeListener);
    }

    public static DriverHomeModel getInstance() {
        if (instance == null) {
            instance = new DriverHomeModel();
        }
        return instance;
    }

    public interface DriverHomeListener {
        void getLocationSuccess(String address);
        void getWashServicesSuccess(WashServicesBean washServicesBean);
        void getWashServicesFail();
        void getRealTimeWeatherSuccess(WeatherRealTimeBean weatherRealTimeBean);
        void getRealTimeWeatherFailed();
        void getForecastWeatherSuccess(WeatherForecastBean weatherForecastBean);
        void getForecastWeatherFailed();
    }

    public void getNearWashServices(){
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(WashService.class)
                .getNearbyWashServiceList(longitude,latitude,4,1,9999)
                .enqueue(new Callback<WashServicesBean>() {
                    @Override
                    public void onResponse(Call<WashServicesBean> call, Response<WashServicesBean> response) {
                        for (DriverHomeListener driverHomeListener : driverHomeListenerList) {
                            driverHomeListener.getWashServicesSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<WashServicesBean> call, Throwable t) {
                        for (DriverHomeListener driverHomeListener : driverHomeListenerList) {
                            driverHomeListener.getWashServicesFail();
                        }
                    }
                });
    }

    public void getRealTimeWeather(){
        RetrofitClient.newInstance(ApiField.getBaseWeatherUrl(longitude,latitude))
                .create(WeatherService.class)
                .getRealTime()
                .enqueue(new Callback<WeatherRealTimeBean>() {
                    @Override
                    public void onResponse(Call<WeatherRealTimeBean> call, Response<WeatherRealTimeBean> response) {
                        weatherRealTimeBean = response.body();
                        for (DriverHomeListener driverHomeListener : driverHomeListenerList) {
                            driverHomeListener.getRealTimeWeatherSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRealTimeBean> call, Throwable t) {
                        for (DriverHomeListener driverHomeListener : driverHomeListenerList) {
                            driverHomeListener.getRealTimeWeatherFailed();
                        }
                    }
                });
    }

    public void getForecastWeather(){
        RetrofitClient.newInstance(ApiField.getBaseWeatherUrl(longitude,latitude))
                .create(WeatherService.class)
                .getRorecast()
                .enqueue(new Callback<WeatherForecastBean>() {
                    @Override
                    public void onResponse(Call<WeatherForecastBean> call, Response<WeatherForecastBean> response) {
                        weatherForecastBean = response.body();
                        for (DriverHomeListener driverHomeListener : driverHomeListenerList) {
                            driverHomeListener.getForecastWeatherSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherForecastBean> call, Throwable t) {
                        for (DriverHomeListener driverHomeListener : driverHomeListenerList) {
                            driverHomeListener.getForecastWeatherFailed();
                        }
                    }
                });
    }


    public void updateLocation(){
        if(mLocationClient!=null)
            mLocationClient.startLocation();
    }

    public void initLocation(Context context){
        AMapLocationListener mLocationListener = aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    longitude = aMapLocation.getLongitude();
                    latitude = aMapLocation.getLatitude();
                    address = aMapLocation.getAddress();
                    for (DriverHomeListener driverHomeListener : driverHomeListenerList) {
                        driverHomeListener.getLocationSuccess(address);
                    }
                    getNearWashServices();
                    getRealTimeWeather();
                    getForecastWeather();
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        };
        mLocationClient = new AMapLocationClient(context);
        mLocationClient.setLocationListener(mLocationListener);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }


    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public WeatherRealTimeBean getWeatherRealTimeBean() {
        return weatherRealTimeBean;
    }

    public WeatherForecastBean getWeatherForecastBean() {
        return weatherForecastBean;
    }

    public static void release() {
        instance.driverHomeListenerList = null;
        instance = null;
    }

}
