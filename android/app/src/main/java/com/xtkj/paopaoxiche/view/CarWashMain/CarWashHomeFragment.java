package com.xtkj.paopaoxiche.view.CarWashMain;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.SkyconValues;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.widget.MarqueeTextView;
import com.xtkj.paopaoxiche.widget.NoScrollListView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CarWashHomeFragment extends BaseFragmemt implements ICarWashContract.IInfoView {

    ICarWashContract.IInfoPresenter infoPresenter;

    @BindView(R.id.bg_weather)
    ImageView bgWeather;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.weather_details1)
    LinearLayout weatherDetails1;
    @BindView(R.id.describe)
    MarqueeTextView describe;
    @BindView(R.id.skycon)
    TextView skycon;
    @BindView(R.id.temperature_low)
    TextView temperatureLow;
    @BindView(R.id.temperature_high)
    TextView temperatureHigh;
    @BindView(R.id.weather_details2)
    LinearLayout weatherDetails2;
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.location_text)
    MarqueeTextView locationText;
    @BindView(R.id.location)
    LinearLayout location;
    @BindView(R.id.more_button)
    Button moreButton;
    @BindView(R.id.wash_list_view)
    NoScrollListView washListView;
    @BindView(R.id.goods_wash_icon_image_view)
    ImageView goodsWashIconImageView;
    @BindView(R.id.goods_wash_name_text_view)
    TextView goodsWashNameTextView;
    @BindView(R.id.car_wash_home_background)
    RelativeLayout carWashHomeBackground;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View carWashView = inflater.inflate(R.layout.fragment_car_wash_home, null);

        unbinder = ButterKnife.bind(this, carWashView);
        return carWashView;
    }

    @Override
    public void setPresenter(ICarWashContract.IInfoPresenter iInfoPresenter) {
        infoPresenter = iInfoPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Context getActivityContext() {
        return getContext();
    }

    @Override
    public void setAddress(String address) {
        locationText.setText(address);
    }

    @Override
    public void setRealTimeWeather(WeatherRealTimeBean weatherRealTimeBean) {
        DecimalFormat myformat = new DecimalFormat("0.0");
        DecimalFormat myformat2 = new DecimalFormat("0");
        temperature.setText(String.format("%s°", Math.round(weatherRealTimeBean.getResult().getTemperature())));
        skycon.setText(SkyconValues.cnNameMap.get(weatherRealTimeBean.getResult().getSkycon()));
        wind.setText(String.format("风速%s", myformat.format(weatherRealTimeBean.getResult().getWind().getSpeed())));
        humidity.setText(String.format("湿度%s%%", myformat2.format(weatherRealTimeBean.getResult().getHumidity() * 100)));
        bgWeather.setImageResource(SkyconValues.homeIconMap.get(weatherRealTimeBean.getResult().getSkycon()));
        carWashHomeBackground.setBackgroundResource(SkyconValues.weatherBgMap.get(weatherRealTimeBean.getResult().getSkycon()));
    }

    @Override
    public void setForecastWeather(WeatherForecastBean weatherForecastBean) {
        DecimalFormat myformat = new DecimalFormat("0.0");
        temperatureHigh.setText(myformat.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMax()));
        temperatureLow.setText(myformat.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMin()));
        describe.setText(weatherForecastBean.getResult().getForecast_keypoint());
    }
}
