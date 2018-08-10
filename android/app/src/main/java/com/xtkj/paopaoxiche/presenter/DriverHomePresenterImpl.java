package com.xtkj.paopaoxiche.presenter;

import android.util.Log;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverMainModel;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.service.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DriverHomePresenterImpl implements IDriverContract.IHomePresenter {

    private IDriverContract.IHomeView homeView;
    private AMapLocationClient mLocationClient =null;
    private DriverMainModel viewModel;

    public DriverHomePresenterImpl(IDriverContract.IHomeView iHomeView) {
        viewModel = DriverMainModel.getInstance();
        homeView = iHomeView;
        homeView.setPresenter(this);
    }


    @Override
    public void onCreate() {

        initLocation();


    }

    @Override
    public void onDestroy() {

    }

    private void initLocation(){
        AMapLocationListener mLocationListener = aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    homeView.setAddress(aMapLocation.getAddress());
                    getNearWashServices(aMapLocation.getLongitude(),aMapLocation.getLatitude());
                    getRealTimeWeather(aMapLocation.getLongitude(),aMapLocation.getLatitude());
                    getForecastWeather(aMapLocation.getLongitude(),aMapLocation.getLatitude());
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        };
        mLocationClient = new AMapLocationClient(homeView.getActivityContext());
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
    public void updateLocation() {
        if(mLocationClient!=null)
            mLocationClient.startLocation();
    }

    private void getNearWashServices(Double lng,Double lat){


        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(WashService.class)
                .getNearbyWashServiceList(lng,lat,4,1,9999)
                .enqueue(new Callback<WashServicesBean>() {
                    @Override
                    public void onResponse(Call<WashServicesBean> call, Response<WashServicesBean> response) {
                       homeView.setWashService(response.body());
                    }

                    @Override
                    public void onFailure(Call<WashServicesBean> call, Throwable t) {

                    }
                });
    }

    private void getRealTimeWeather(double j,double w){

        viewModel.getRealTimeWeather(j,w)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherRealTimeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherRealTimeBean weatherRealTimeBean) {
                        homeView.setRealTimeWeather(weatherRealTimeBean);
                    }
                });

    }
    private void getForecastWeather(double j,double w){

        viewModel.getForecastWeather(j,w)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WeatherForecastBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WeatherForecastBean weatherForecastBean) {
                        homeView.setForecastWeather(weatherForecastBean);
                    }
                });
    }
}
