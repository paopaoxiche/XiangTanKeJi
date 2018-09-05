package com.xtkj.paopaoxiche.view.CarWashMain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.SkyconValues;
import com.xtkj.paopaoxiche.base.BaseFragmemt;
import com.xtkj.paopaoxiche.bean.RecentWashListBean;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.bean.WashServiceListBean;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.bean.WeatherForecastBean;
import com.xtkj.paopaoxiche.bean.WeatherRealTimeBean;
import com.xtkj.paopaoxiche.contract.ICarWashContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.model.WashServerModel;
import com.xtkj.paopaoxiche.service.WashService;
import com.xtkj.paopaoxiche.view.WeatherForecast.WeatherForecastActivity;
import com.xtkj.paopaoxiche.view.view.WashCarListDialog;
import com.xtkj.paopaoxiche.widget.MarqueeTextView;
import com.xtkj.paopaoxiche.widget.NoScrollListView;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.goods_recycler)
    RecyclerView goodsRecylerView;

    Unbinder unbinder;
    private CarWashHomeFragment.ShopItemsAdapter shopItemsAdapter = null;

    private RecentWashAdapter recentWashAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View carWashView = inflater.inflate(R.layout.fragment_car_wash_home, null);

        unbinder = ButterKnife.bind(this, carWashView);
        infoPresenter.onCreate();
        goodsRecylerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        if (UserInfo.getAvatar() != null && UserInfo.getAvatar().length() > 0) {
            Glide.with(getActivity()).load(UserInfo.getAvatar()).into(goodsWashIconImageView);
        }

        getWashList();

        return carWashView;
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
        shopItemsAdapter = new ShopItemsAdapter(washShopBean);
        goodsRecylerView.setAdapter(shopItemsAdapter);
    }

    @OnClick({R.id.location, R.id.weather_details1, R.id.weather_details2, R.id.more_button})
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
        }
    }

    class ShopItemsAdapter extends RecyclerView.Adapter<CarWashHomeFragment.ShopItemsAdapter.ViewHolder> {


        private WashCommodityBean data;

        ShopItemsAdapter(WashCommodityBean washCommodityBean) {
            this.data = washCommodityBean;
        }

        @Override
        public CarWashHomeFragment.ShopItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_selling_goods, viewGroup, false);
            return new CarWashHomeFragment.ShopItemsAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CarWashHomeFragment.ShopItemsAdapter.ViewHolder holder, int position) {
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
}
