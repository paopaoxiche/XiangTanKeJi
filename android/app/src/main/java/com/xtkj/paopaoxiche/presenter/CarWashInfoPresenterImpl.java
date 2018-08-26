package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.model.GoodsModel;
import com.xtkj.paopaoxiche.model.UserInfo;

public class CarWashInfoPresenterImpl implements ICarWashContract.IInfoPresenter,
        DriverHomeModel.DriverHomeListener, GoodsModel.GoodsListener {

    ICarWashContract.IInfoView infoView;
    DriverHomeModel driverHomeModel;
    GoodsModel goodsModel;

    public CarWashInfoPresenterImpl(ICarWashContract.IInfoView iInfoView) {
        infoView = iInfoView;
        infoView.setPresenter(this);
    }

    @Override
    public void onCreate() {
        driverHomeModel = DriverHomeModel.getInstance();
        goodsModel = GoodsModel.getInstance();

        driverHomeModel.addListener(this);
        goodsModel.addListener(this);
        initLocation();

        goodsModel.getCarWashGoods(UserInfo.getWashId(), 0, 20);
    }

    @Override
    public void onDestroy() {
        DriverHomeModel.getInstance().removeListener(this);
        DriverHomeModel.release();
        GoodsModel.getInstance().removeListener(this);
        GoodsModel.release();
    }

    private void initLocation(){
        driverHomeModel.initLocation(infoView.getActivityContext());
    }

    @Override
    public void updateLocation() {
        driverHomeModel.updateLocation();
    }


    @Override
    public void getLocationSuccess(String address) {
        infoView.setAddress(address);
    }

    @Override
    public void getWashServicesSuccess(WashServicesBean washServicesBean) {

    }

    @Override
    public void getWashServicesFail() {

    }

    @Override
    public void getRealTimeWeatherSuccess(WeatherRealTimeBean weatherRealTimeBean) {
        infoView.setRealTimeWeather(weatherRealTimeBean);
    }

    @Override
    public void getRealTimeWeatherFailed() {

    }

    @Override
    public void getForecastWeatherSuccess(WeatherForecastBean weatherForecastBean) {
        infoView.setForecastWeather(weatherForecastBean);
    }

    @Override
    public void getForecastWeatherFailed() {

    }

    @Override
    public void getCommoditySuccess(WashShopBean washShopBean) {

    }


    @Override
    public void getCommodityFailed() {

    }

    @Override
    public void getCarWashGoodsSuccess(WashCommodityBean washCommodityBean) {
        infoView.setGoodsInfoList(washCommodityBean);
    }
}
