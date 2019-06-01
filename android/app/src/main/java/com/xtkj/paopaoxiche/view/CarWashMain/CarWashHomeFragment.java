package com.xtkj.paopaoxiche.view.CarWashMain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.SkyconValues;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.BannerAdBean;
import com.xtkj.paopaoxiche.bean.RecentWashListBean;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverHomeModel;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.service.AdService;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.view.DriverMain.Ad.AdActivity;
import com.xtkj.paopaoxiche.view.DriverMain.HomeClass.HomeClassActivity;
import com.xtkj.paopaoxiche.view.WeatherForecast.WeatherForecastActivity;
import com.xtkj.paopaoxiche.view.WebView.WebViewActivity;
import com.xtkj.paopaoxiche.view.view.WashCarListDialog;
import com.xtkj.paopaoxiche.widget.GlideImageLoader;
import com.xtkj.paopaoxiche.widget.MarqueeTextView;
import com.xtkj.paopaoxiche.widget.NoScrollListView;
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

public class CarWashHomeFragment extends BaseFragmemt implements ICarWashContract.IInfoView {

    ICarWashContract.IInfoPresenter infoPresenter;

    Handler handler;
    int ad_index = 0;
//    int SIGN = 101;

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
    //    @BindView(R.id.goods_wash_icon_image_view)
//    ImageView goodsWashIconImageView;
//    @BindView(R.id.goods_wash_name_text_view)
//    TextView goodsWashNameTextView;
    @BindView(R.id.car_wash_home_background)
    RelativeLayout carWashHomeBackground;
    //    @BindView(R.id.goods_recycler)
//    RecyclerView goodsRecylerView;
    @BindView(R.id.count_wash_text_view)
    TextView washCountTextView;
    @BindView(R.id.count_user_text_view)
    TextView userCountTextView;
    @BindView(R.id.my_banner)
    Banner myBanner;

    Unbinder unbinder;
    @BindView(R.id.ad_text)
    MarqueeTextView adText;
    @BindView(R.id.ad_more)
    Button adMore;
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
    //private CarWashHomeFragment.ShopItemsAdapter shopItemsAdapter = null;

    private RecentWashAdapter recentWashAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View carWashView = inflater.inflate(R.layout.fragment_car_wash_home, null);

        unbinder = ButterKnife.bind(this, carWashView);
        infoPresenter.onCreate();
        //goodsRecylerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        if (UserInfo.getAvatar() != null && UserInfo.getAvatar().length() > 0) {
//            Glide.with(getActivity()).load(UserInfo.getAvatar()).into(goodsWashIconImageView);
//        }

        homeClassButton1.setText(AppConstant.HOME_CLASS_1);
        homeClassButton2.setText(AppConstant.HOME_CLASS_2);
        homeClassButton3.setText(AppConstant.HOME_CLASS_3);
        homeClassButton4.setText(AppConstant.HOME_CLASS_4);
        homeClassButton5.setText(AppConstant.HOME_CLASS_5);


//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                //临时方案，可能造成activity不能回收，后面需要改一下
//                if (adText == null) {
//                    handler.sendEmptyMessageDelayed(msg.what, 500);
//                    return;
//                }
//                if (msg.what == SIGN) {
//                    if (ad_index < DriverHomeModel.getInstance().getAdStrings().size()) {
//                        adText.setText(DriverHomeModel.getInstance().getAdStrings().get(ad_index));
//                    }
//                    ad_index++;
//                    if (ad_index >= DriverHomeModel.getInstance().getAdStrings().size())
//                        ad_index = 0;
//                }
//            }
//        };

        getWashList();
        initValue();

        return carWashView;
    }

    void initValue() {
        checkBannerAd();
        userCountTextView.setText(UserInfo.getCountUser() + "");
        washCountTextView.setText(UserInfo.getCountWash() + "");
    }

    @Override
    public void setPresenter(ICarWashContract.IInfoPresenter iInfoPresenter) {
        infoPresenter = iInfoPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        infoPresenter.onDestroy();
        unbinder.unbind();
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
        carWashHomeBackground.setBackgroundResource(SkyconValues.weatherBgMap.get(weatherRealTimeBean.getResult().getSkycon()));
    }

    @Override
    public void setForecastWeather(WeatherForecastBean weatherForecastBean) {
        DecimalFormat myformat = new DecimalFormat("0.0");
        temperatureHigh.setText(myformat.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMax()));
        temperatureLow.setText(myformat.format(weatherForecastBean.getResult().getDaily().getTemperature().get(0).getMin()));
        describe.setText(weatherForecastBean.getResult().getForecast_keypoint());
    }

    @Override
    public void setGoodsInfoList(WashCommodityBean washShopBean) {
//        shopItemsAdapter = new ShopItemsAdapter(washShopBean);
//        goodsRecylerView.setAdapter(shopItemsAdapter);
    }

    @OnClick({R.id.ad_more, R.id.location, R.id.weather_details1, R.id.weather_details2, R.id.more_button, R.id.home_class_button_1, R.id.home_class_button_2, R.id.home_class_button_3, R.id.home_class_button_4, R.id.home_class_button_5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location:
                infoPresenter.updateLocation();
                break;
            case R.id.weather_details1:
            case R.id.weather_details2:
                Intent intent2 = new Intent(getActivity(), WeatherForecastActivity.class);
                startActivity(intent2);
                break;
            case R.id.more_button:
                new WashCarListDialog(getContext(), true).show();
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

    class ShopItemsAdapter extends RecyclerView.Adapter<ShopItemsAdapter.ViewHolder> {


        private WashCommodityBean data;

        ShopItemsAdapter(WashCommodityBean washCommodityBean) {
            this.data = washCommodityBean;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_selling_goods, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(getActivity()).load(data.getData().get(position).getImage()).into(holder.sellingGoodsImg);
            holder.sellingGoodsPrice1.setText(String.format("¥%s", data.getData().get(position).getCurrentPrice()));
            holder.sellingGoodsPrice2.setText(String.format("¥%s", data.getData().get(position).getOriginPrice()));
            holder.sellingGoodsPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        @Override
        public int getItemCount() {
            if (data == null || data.getData() == null) {
                return 0;
            }
            return data.getData().size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.selling_goods_img)
            ImageView sellingGoodsImg;
            @BindView(R.id.selling_goods_price1)
            TextView sellingGoodsPrice1;
            @BindView(R.id.selling_goods_price2)
            TextView sellingGoodsPrice2;

            ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    public void getWashList() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getRecentWashList(UserInfo.getWashId(), 4)
                .enqueue(new Callback<RecentWashListBean>() {
                    @Override
                    public void onResponse(Call<RecentWashListBean> call, Response<RecentWashListBean> response) {
                        if (response == null || response.body() == null) {
                            return;
                        }
                        if (response.body().getCode() != 401) {
                            recentWashAdapter = new RecentWashAdapter(getContext(), response.body().getData());
                            washListView.setAdapter(recentWashAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<RecentWashListBean> call, Throwable t) {

                    }
                });
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
                                if (position < dataBean.size()) {
                                    Intent intent = new Intent(CarWashHomeFragment.this.getContext(), WebViewActivity.class);
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
