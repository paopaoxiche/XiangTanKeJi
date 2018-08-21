package com.xtkj.paopaoxiche.view.WeatherForecast;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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
import com.xtkj.paopaoxiche.widget.MarqueeTextView;

import java.text.DecimalFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherForecastActivity extends BaseGaodeActivity implements IWeatherForecastContract.IWeatherForecastView {

    IWeatherForecastContract.IWeatherForecastPresenter weatherForecastPresenterImpl = null;
    private static final String[] WEEK_NAME = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.location_text)
    MarqueeTextView locationText;
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
    @BindView(R.id.skycon)
    TextView skycon;
    @BindView(R.id.forecast)
    MarqueeTextView forecast;
    @BindView(R.id.date1)
    TextView date1;
    @BindView(R.id.week1)
    TextView week1;
    @BindView(R.id.sky_img)
    ImageView skyImg;
    @BindView(R.id.high1)
    TextView high1;
    @BindView(R.id.low1)
    TextView low1;
    @BindView(R.id.date2)
    TextView date2;
    @BindView(R.id.week2)
    TextView week2;
    @BindView(R.id.sky_img2)
    ImageView skyImg2;
    @BindView(R.id.high2)
    TextView high2;
    @BindView(R.id.low2)
    TextView low2;
    @BindView(R.id.date3)
    TextView date3;
    @BindView(R.id.week3)
    TextView week3;
    @BindView(R.id.sky_img3)
    ImageView skyImg3;
    @BindView(R.id.high3)
    TextView high3;
    @BindView(R.id.low3)
    TextView low3;
    @BindView(R.id.date4)
    TextView date4;
    @BindView(R.id.week4)
    TextView week4;
    @BindView(R.id.sky_img4)
    ImageView skyImg4;
    @BindView(R.id.high4)
    TextView high4;
    @BindView(R.id.low4)
    TextView low4;
    @BindView(R.id.date5)
    TextView date5;
    @BindView(R.id.week5)
    TextView week5;
    @BindView(R.id.sky_img5)
    ImageView skyImg5;
    @BindView(R.id.high5)
    TextView high5;
    @BindView(R.id.low5)
    TextView low5;
    @BindView(R.id.forecast_bg)
    LinearLayout forecastBg;
    @BindView(R.id.bg_weather)
    LinearLayout bgWeather;


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
        skycon.setText(SkyconValues.cnNameMap.get(weatherRealTimeBean.getResult().getSkycon()));
        forecastBg.setBackgroundResource(SkyconValues.forecastBgMap.get(weatherRealTimeBean.getResult().getSkycon()));
    }

    @Override
    public void setForecastWeather(WeatherForecastBean weatherForecastBean) {
        DecimalFormat myformat1 = new DecimalFormat("0.0");
        DecimalFormat myformat0 = new DecimalFormat("0");
        temperatureHigh.setText(String.format("%s°", myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMax())));
        temperatureLow.setText(String.format("%s°", myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMin())));
        forecast.setText(weatherForecastBean.getResult().getForecast_keypoint());

        date1.setText(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getDate().substring(5, 10).replace("-", "/"));
        date2.setText(weatherForecastBean.getResult().getDaily().getTemperature().get(1).getDate().substring(5, 10).replace("-", "/"));
        date3.setText(weatherForecastBean.getResult().getDaily().getTemperature().get(2).getDate().substring(5, 10).replace("-", "/"));
        date4.setText(weatherForecastBean.getResult().getDaily().getTemperature().get(3).getDate().substring(5, 10).replace("-", "/"));
        date5.setText(weatherForecastBean.getResult().getDaily().getTemperature().get(4).getDate().substring(5, 10).replace("-", "/"));

        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_WEEK);
        week1.setText("今天");
        week2.setText(WEEK_NAME[(i) % 7 -1]);
        week3.setText(WEEK_NAME[(i + 1) % 7 -1]);
        week4.setText(WEEK_NAME[(i + 2) % 7 -1]);
        week5.setText(WEEK_NAME[(i + 3) % 7 -1]);

        skyImg.setImageResource(SkyconValues.forecastIconMap.get(weatherForecastBean.getResult().getDaily().getSkycon().get(0).getValue()));
        skyImg2.setImageResource(SkyconValues.forecastIconMap.get(weatherForecastBean.getResult().getDaily().getSkycon().get(1).getValue()));
        skyImg3.setImageResource(SkyconValues.forecastIconMap.get(weatherForecastBean.getResult().getDaily().getSkycon().get(2).getValue()));
        skyImg4.setImageResource(SkyconValues.forecastIconMap.get(weatherForecastBean.getResult().getDaily().getSkycon().get(3).getValue()));
        skyImg5.setImageResource(SkyconValues.forecastIconMap.get(weatherForecastBean.getResult().getDaily().getSkycon().get(4).getValue()));

        high1.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMax()));
        high2.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(1).getMax()));
        high3.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(2).getMax()));
        high4.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(3).getMax()));
        high5.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(4).getMax()));

        low1.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMin()));
        low2.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(1).getMin()));
        low3.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(2).getMin()));
        low4.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(3).getMin()));
        low5.setText(myformat0.format(weatherForecastBean.getResult().getDaily().getTemperature().get(4).getMin()));

    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @OnClick({R.id.back_button, R.id.location_text, R.id.temperature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                finish();
                break;
        }
    }
}
