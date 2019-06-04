package com.xtkj.paopaoxiche.view.DriverMain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.SkyconValues;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.ADBean;
import com.xtkj.paopaoxiche.bean.BannerAdBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.IDriverContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.service.AdService;
import com.xtkj.paopaoxiche.utils.PreferUtils;
import com.xtkj.paopaoxiche.view.DriverMain.Ad.AdActivity;
import com.xtkj.paopaoxiche.view.DriverMain.HomeClass.HomeClassActivity;
import com.xtkj.paopaoxiche.view.DriverMap.DriverMapActivity;
import com.xtkj.paopaoxiche.view.WeatherForecast.WeatherForecastActivity;
import com.xtkj.paopaoxiche.view.WebView.WebViewActivity;
import com.xtkj.paopaoxiche.widget.GlideImageLoader;
import com.xtkj.paopaoxiche.widget.MarqueeTextView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.count_wash_text_view)
    TextView washCountTextView;
    @BindView(R.id.count_user_text_view)
    TextView userCountTextView;
    @BindView(R.id.home_class_button_1)
    TextView homeClassButton1;
    @BindView(R.id.home_class_button_2)
    TextView homeClassButton2;
    @BindView(R.id.home_class_button_3)
    TextView homeClassButton3;
    @BindView(R.id.home_class_button_4)
    TextView homeClassButton4;
    @BindView(R.id.home_class_button_5)
    TextView homeClassButton5;
    @BindView(R.id.ad_text)
    MarqueeTextView adText;
    @BindView(R.id.ad_more)
    Button adMore;
    @BindView(R.id.my_banner)
    Banner myBanner;

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
        initValue();
        return view;
    }

    @SuppressLint("HandlerLeak")
    void initView() {
        washServiceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivityContext()));

        homeClassButton1.setText(AppConstant.HOME_CLASS_1);
        homeClassButton2.setText(AppConstant.HOME_CLASS_2);
        homeClassButton3.setText(AppConstant.HOME_CLASS_3);
        homeClassButton4.setText(AppConstant.HOME_CLASS_4);
        homeClassButton5.setText(AppConstant.HOME_CLASS_5);

        checkBannerAd();
    }

    void initValue() {
        userCountTextView.setText(UserInfo.getCountUser() + "");
        washCountTextView.setText(UserInfo.getCountWash() + "");
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
        wind.setText(String.format("风速\n%s", myformat.format(weatherRealTimeBean.getResult().getWind().getSpeed())));
        humidity.setText(String.format("湿度\n%s%%", myformat2.format(weatherRealTimeBean.getResult().getHumidity() * 100)));
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
        homeWashServiceAdapter.notifyDataSetChanged();

        if (DriverHomeModel.getInstance().getWashServicesBean().getData().size() == 0) {
            noWashService.setVisibility(View.VISIBLE);
        } else {
            noWashService.setVisibility(View.GONE);
        }

    }

    @Override
    public void updateCommodity() {
        // 此处可能为null 需要定位为什么为null
        WashShopBean bean = DriverHomeModel.getInstance().getWashShopBean();
        if (bean == null) {
            return;
        }
        int num = bean.getData().size();

        homeShopFragmentAdapter = new HomeShopFragmentAdapter(getFragmentManager(), bean.getData());
        shopViewpager.setAdapter(homeShopFragmentAdapter);
        shopViewpager.setCurrentItem(0);

        shopViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

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


        indicator.removeAllViews();
        for (int i = 0; i < num; i++) {
            View view = new View(getActivityContext());
            view.setBackgroundResource(R.drawable.home_indicator);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            layoutParams.leftMargin = 10;
            indicator.addView(view, layoutParams);
        }
        if (indicator.getChildAt(0) != null)
            indicator.getChildAt(0).setEnabled(true);
        shopViewpager.setCurrentItem(0);
    }

    @Override
    public void startAd() {
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Message msg = new Message();
//                msg.what = SIGN;
//                handler.sendMessage(msg);
//            }
//        }, 0, 10000);

        ArrayList<String> arrayList = DriverHomeModel.getInstance().getAdStrings();
        adText.setText(arrayList.get(arrayList.size() - 1));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homePresenter.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        //null.unbind();
        //null.unbind();
    }

    @OnClick({R.id.ad_more, R.id.location, R.id.more_wash_yard, R.id.weather_details1, R.id.weather_details2, R.id.home_class_button_1, R.id.home_class_button_2, R.id.home_class_button_3, R.id.home_class_button_4, R.id.home_class_button_5})
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
            case R.id.home_class_button_1:
                Intent intent3 = new Intent(getActivity(), HomeClassActivity.class);
                intent3.putExtra("title", AppConstant.HOME_CLASS_1);
                startActivity(intent3);
                break;
            case R.id.home_class_button_2:
                Intent intent4 = new Intent(getActivity(), HomeClassActivity.class);
                intent4.putExtra("title", AppConstant.HOME_CLASS_2);
                startActivity(intent4);
                break;
            case R.id.home_class_button_3:
                Intent intent5 = new Intent(getActivity(), HomeClassActivity.class);
                intent5.putExtra("title", AppConstant.HOME_CLASS_3);
                startActivity(intent5);
                break;
            case R.id.home_class_button_4:
                Intent intent6 = new Intent(getActivity(), HomeClassActivity.class);
                intent6.putExtra("title", AppConstant.HOME_CLASS_4);
                startActivity(intent6);
                break;
            case R.id.home_class_button_5:
                Intent intent7 = new Intent(getActivity(), HomeClassActivity.class);
                intent7.putExtra("title", AppConstant.HOME_CLASS_5);
                startActivity(intent7);
                break;
            case R.id.ad_more:
                Intent intent8 = new Intent(getActivity(), AdActivity.class);
                startActivity(intent8);
                break;
        }
    }


    private void checkBannerAd() {
        RetrofitClient.newInstance(ApiField.BASEURL)
                .create(AdService.class)
                .getBannerAd()
                .enqueue(new Callback<BannerAdBean>() {
                    @Override
                    public void onResponse(Call<BannerAdBean> call, Response<BannerAdBean> response) {
                        if (response.body().getCode() != 200) {
                            return;
                        }
                        List<BannerAdBean.DataBean> dataBean = response.body().getData();
                        if (dataBean == null || dataBean.size() == 0) {
                            return;
                        }

                        myBanner.setImageLoader(new GlideImageLoader());
                        myBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        //设置图片集合
                        List<String> urls = new ArrayList<>();
                        for (BannerAdBean.DataBean bean : dataBean) {
                            urls.add(bean.getImg());
                        }
                        myBanner.setImages(urls);
                        myBanner.setDelayTime(4000);
                        myBanner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                if (position < dataBean.size() && !TextUtils.isEmpty(dataBean.get(position).getUrl())) {
                                    Intent intent = new Intent(HomeFragment.this.getContext(), WebViewActivity.class);
                                    intent.putExtra(AppConstant.WEB_NTENT_URL, dataBean.get(position).getUrl());
                                    startActivity(intent);
                                }
                            }
                        });
                        //banner设置方法全部调用完毕时最后调用
                        myBanner.start();
                    }

                    @Override
                    public void onFailure(Call<BannerAdBean> call, Throwable t) {

                    }
                });
    }

}
