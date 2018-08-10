package com.xtkj.paopaoxiche.view.WeatherForecast;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.SkyconValues;
import com.xtkj.paopaoxiche.base.BaseGaodeActivity;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.IWeatherForecastContract;
import com.xtkj.paopaoxiche.presenter.WeatherForecastPresenterImpl;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherForecastActivity extends BaseGaodeActivity implements IWeatherForecastContract.IWeatherForecastView {

    IWeatherForecastContract.IWeatherForecastPresenter weatherForecastPresenterImpl = null;
    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.location_text)
    TextView locationText;
    @BindView(R.id.bg_weather)
    LinearLayout bgWeather;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.temperature_high)
    TextView temperatureHigh;
    @BindView(R.id.temperature_low)
    TextView temperatureLow;
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.humidity)
    TextView humidity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        ButterKnife.bind(this);

        initViews();
        initValues();
        initListeners();

        new WeatherForecastPresenterImpl(this);
        weatherForecastPresenterImpl.onCreate();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initValues() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void setPresenter(IWeatherForecastContract.IWeatherForecastPresenter iWeatherForecastPresenter) {
        weatherForecastPresenterImpl = iWeatherForecastPresenter;
    }

    @Override
    public void setAddress(String address) {
        locationText.setText(address);
    }

    @Override
    public void setRealTimeWeather(WeatherRealTimeBean weatherRealTimeBean) {
        DecimalFormat myformat1 = new DecimalFormat("0.0");
        DecimalFormat myformat0 = new DecimalFormat("0");
        temperature.setText(String.format("%s°", myformat0.format(weatherRealTimeBean.getResult().getTemperature())));
        wind.setText(String.format("风速%s", myformat1.format(weatherRealTimeBean.getResult().getWind().getSpeed())));
        humidity.setText(String.format("湿度%s%%", myformat1.format(weatherRealTimeBean.getResult().getHumidity() * 100)));
        bgWeather.setBackgroundResource(SkyconValues.weatherBgMap.get(weatherRealTimeBean.getResult().getSkycon()));
    }

    @Override
    public void setForecastWeather(WeatherForecastBean weatherForecastBean) {
        DecimalFormat myformat1 = new DecimalFormat("0.0");
        DecimalFormat myformat0 = new DecimalFormat("0");
        temperatureHigh.setText(String.format("%s°", myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMax())));
        temperatureLow.setText(String.format("%s°", myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMin())));

    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
