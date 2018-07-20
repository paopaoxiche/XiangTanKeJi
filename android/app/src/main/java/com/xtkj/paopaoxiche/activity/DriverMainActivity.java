package com.xtkj.paopaoxiche.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.bean.WeatherLiveBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.presenter.DriverPresenterImpl;

public class DriverMainActivity extends BaseActivity implements IDriverContract.IDriverView{
    IDriverContract.IDriverPresenter driverPresenter;

    TextView tv_temperature;
    TextView tv_rain;
    TextView tv_windpower;
    TextView tv_winddirect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        initViews();
        initValues();
        initListeners();

        new DriverPresenterImpl(this);
        driverPresenter.onCreate();
    }

    @Override
    protected void initViews() {
        tv_temperature = findViewById(R.id.driver_temperature);
        tv_rain = findViewById(R.id.driver_rain);
        tv_winddirect = findViewById(R.id.driver_wind_direct);
        tv_windpower = findViewById(R.id.driver_wind_power);
    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void setWeather(WeatherLiveBean weatherLiveBean) {
        tv_temperature.setText(String.format("%s℃", weatherLiveBean.getData().getTemperature()));
        tv_windpower.setText(weatherLiveBean.getData().getWindpower());
        tv_winddirect.setText(weatherLiveBean.getData().getWinddirect());
        tv_rain.setText((weatherLiveBean.getData().getRain()));
    }

    @Override
    public void setPresenter(IDriverContract.IDriverPresenter iDriverPresenter) {
        this.driverPresenter = iDriverPresenter;
    }


}
