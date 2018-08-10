package com.xtkj.paopaoxiche.presenter;

import android.util.Log;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xtkj.paopaoxiche.contract.IWeatherForecastContract;

public class WeatherForecastPresenterImpl implements IWeatherForecastContract.IWeatherForecastPresenter {


    private IWeatherForecastContract.IWeatherForecastView weatherForecastView;
    private AMapLocationClient mLocationClient =null;

    public WeatherForecastPresenterImpl(IWeatherForecastContract.IWeatherForecastView iWeatherForecastView) {
        weatherForecastView = iWeatherForecastView;
        weatherForecastView.setPresenter(this);
    }
    @Override
    public void onCreate() {
        initLocation();
    }

    private void initLocation(){
        AMapLocationListener mLocationListener = aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    weatherForecastView.setAddress(aMapLocation.getAddress());
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        };
        mLocationClient = new AMapLocationClient(weatherForecastView.getActivityContext());
        mLocationClient.setLocationListener(mLocationListener);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();

        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);

        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);
        mLocationClient.setLocationOption(mLocationOption);

        mLocationClient.startLocation();
    }

    @Override
    public void onDestroy() {

    }
}
