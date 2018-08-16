package com.xtkj.paopaoxiche.view.DriverMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.SkyconValues;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.view.DriverMap.DriverMapActivity;
import com.xtkj.paopaoxiche.view.WeatherForecast.WeatherForecastActivity;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragmemt implements IDriverContract.IHomeView {


    IDriverContract.IHomePresenter homePresenter;
    HomeWashServiceAdapter homeWashServiceAdapter = null;
    HomeShopFragmentAdapter homeShopFragmentAdapter = null;
    int viewpager_index = 0;

    Unbinder unbinder;
    @BindView(R.id.temperature)
    TextView temperature;
    @BindView(R.id.describe)
    TextView describe;
    @BindView(R.id.skycon)
    TextView skycon;
    @BindView(R.id.temperature_low)
    TextView temperatureLow;
    @BindView(R.id.temperature_high)
    TextView temperatureHigh;
    @BindView(R.id.wind)
    TextView wind;
    @BindView(R.id.location_text)
    TextView locationText;
    @BindView(R.id.location)
    LinearLayout location;
    @BindView(R.id.more_wash_yard)
    TextView moreWashYard;
    @BindView(R.id.no_wash_service)
    LinearLayout noWashService;
    @BindView(R.id.shop_viewpager)
    ViewPager shopViewpager;
    @BindView(R.id.humidity)
    TextView humidity;
    @BindView(R.id.bg_weather)
    ImageView bgWeather;
    @BindView(R.id.wash_service_recyclerView)
    RecyclerView washServiceRecyclerView;
    @BindView(R.id.home_weather_bg)
    ScrollView homeWeatherBg;
    @BindView(R.id.weather_details1)
    LinearLayout weatherDetails1;
    @BindView(R.id.weather_details2)
    LinearLayout weatherDetails2;
    @BindView(R.id.indicator)
    LinearLayout indicator;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homePresenter.onCreate();
        unbinder = ButterKnife.bind(this, view);

        initView();
        return view;
    }

    void initView() {
        washServiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));
    }

    @Override
    public void setPresenter(IDriverContract.IHomePresenter iHomePresenter) {
        homePresenter = iHomePresenter;
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
        homeWeatherBg.setBackgroundResource(SkyconValues.weatherBgMap.get(weatherRealTimeBean.getResult().getSkycon()));
    }

    @Override
    public void setForecastWeather(WeatherForecastBean weatherForecastBean) {
        DecimalFormat myformat = new DecimalFormat("0.0");
        temperatureHigh.setText(myformat.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMax()));
        temperatureLow.setText(myformat.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMin()));
        describe.setText(weatherForecastBean.getResult().getForecast_keypoint());
    }

    @Override
    public void updateWashServicesBean() {
        if (homeWashServiceAdapter == null) {
            homeWashServiceAdapter = new HomeWashServiceAdapter(DriverHomeModel.getInstance().getWashServicesBean(), getActivityContext());
            washServiceRecyclerView.setAdapter(homeWashServiceAdapter);
        }
        int num = DriverHomeModel.getInstance().getWashServicesBean().getData().size();
        if(num==0)noWashService.setVisibility(View.VISIBLE);
        else{
            noWashService.setVisibility(View.GONE);

            homeShopFragmentAdapter = new HomeShopFragmentAdapter(getFragmentManager(), DriverHomeModel.getInstance().getWashServicesBean().getData());
            shopViewpager.setAdapter(homeShopFragmentAdapter);
            shopViewpager.setCurrentItem(0);

            shopViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                    @Override
                    public void onPageSelected(int position) {
                        indicator.getChildAt(viewpager_index).setEnabled(false);
                        indicator.getChildAt(position).setEnabled(true);
                        viewpager_index = position;
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                        homeShopFragmentAdapter.notifyDataSetChanged();
                    }
                });

        }
        homeWashServiceAdapter.notifyDataSetChanged();

        indicator.removeAllViews();
        for (int i = 0; i < num; i++) {
            View view = new View(getActivityContext());
            view.setBackgroundResource(R.drawable.home_indicator);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            layoutParams.leftMargin = 10;
            indicator.addView(view, layoutParams);
        }
        if(indicator.getChildAt(0)!=null)
        indicator.getChildAt(0).setEnabled(true);


        shopViewpager.setCurrentItem(0);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homePresenter.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @OnClick({R.id.location, R.id.more_wash_yard, R.id.weather_details1, R.id.weather_details2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location:
                homePresenter.updateLocation();
                break;
            case R.id.more_wash_yard:
                Intent intent = new Intent(getActivity(), DriverMapActivity.class);
                startActivity(intent);
                break;
            case R.id.weather_details1:
            case R.id.weather_details2:
                Intent intent2 = new Intent(getActivity(), WeatherForecastActivity.class);
                startActivity(intent2);
                break;
        }
    }



}
