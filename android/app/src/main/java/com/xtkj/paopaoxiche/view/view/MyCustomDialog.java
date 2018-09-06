package com.xtkj.paopaoxiche.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.bumptech.glide.Glide;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.base.CustomAdapter;
import com.xtkj.paopaoxiche.base.CustomAdapter.LayoutView;
import com.xtkj.paopaoxiche.bean.UserConsumeBean;
import com.xtkj.paopaoxiche.bean.UserConsumeBean.DataBean;
import com.xtkj.paopaoxiche.bean.UserConsumeBean.DataBean.CommoditiesBean;
import com.xtkj.paopaoxiche.bean.UserConsumeBean.DataBean.CouponsBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.widget.FullScreenWithStatusBarDialog;
import com.xtkj.paopaoxiche.widget.NoScrollListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCustomDialog extends FullScreenWithStatusBarDialog implements LayoutView, CommitEvaluationDialog.EvaluateCallback {
    @BindView(R.id.back_arrow_image_button)
    ImageButton backArrowImageButton;
    @BindView(R.id.lv_consumption_record)
    ListView lvConsumptionRecord;

    private CustomAdapter<DataBean> adapter;

    public MyCustomDialog(Context context, boolean statusBarVisible) {
        super(context, statusBarVisible);
        setContentView(R.layout.dialog_custom);
        ButterKnife.bind(this);
        findViewById(R.id.back_arrow_image_button).setOnClickListener(backButtonClickListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        new Handler().post(this::getMyConsume);
    }

    private void getMyConsume() {
        if (!isShowing()) {
            return;
        }

        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                      .create(UserService.class)
                      .getMyConsume()
                      .enqueue(new Callback<UserConsumeBean>() {
                          @Override
                          public void onResponse(Call<UserConsumeBean> call, Response<UserConsumeBean> response) {
                              if (!isShowing()) {
                                  return;
                              }

                              UserConsumeBean bean = response.body();
                              if (bean.getCode() != 200) {
                                  Toast.makeText(BaseApplication.getContext(), "获取消费记录失败！", Toast.LENGTH_LONG).show();
                                  return;
                              }

                              List<DataBean> dataBeans = bean.getData();
                              if (dataBeans == null || dataBeans.isEmpty()) {
                                  Toast.makeText(BaseApplication.getContext(), "没有任何消费记录！", Toast.LENGTH_LONG).show();
                                  return;
                              }

                              adapter.updateDataAndNotifyDataChanged(dataBeans);
                          }

                          @Override
                          public void onFailure(Call<UserConsumeBean> call, Throwable t) {
                              if (!isShowing()) {
                                  return;
                              }

                              Toast.makeText(BaseApplication.getContext(), "获取消费记录失败！", Toast.LENGTH_LONG).show();
                          }
                      });
    }

    private SimpleDateFormat sdf;

    private SimpleDateFormat getDateFormat() {
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault());
        }

        return sdf;
    }

    private void initListView() {
        if (adapter == null) {
            adapter = new CustomAdapter<>(new ArrayList<>());
            adapter.setLayoutView(this);
        }
        lvConsumptionRecord.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View setView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_consumption_record, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataBean bean = adapter.getItem(position);
        //设置id
        holder.setServiceId(bean.getId());

        // 设置服务的头部
        Glide.with(getContext())
             .load(bean.getCarWashImg())
             .into(holder.ivConsumeCompanyIcon);
        holder.tvConsumeCompanyName.setText(bean.getCarWashName());
        holder.tvServiceTime.setText(getDateFormat().format(new Date(bean.getTime())));

        // 设置洗车服务
        holder.tvWashCarName.setText(bean.getServiceName());
        holder.tvNormalPrice.setText("￥" + String.valueOf(bean.getPayment()));
        setCouponsBeanView(bean.getCoupons(), holder);

        // 设置最终数目
        holder.tvTotalPrice.setText("合计：￥" + String.valueOf(bean.getTotalPrice()));

        // 设置商品布局
        setGoodsView(bean.getCommodities(), holder);

        if (bean.getIsEvaluation() == 0) {
            holder.buttonEvaluation.setClickable(true);
            holder.buttonEvaluation.setText("评价");
        } else {
            holder.buttonEvaluation.setClickable(false);
            holder.buttonEvaluation.setText("已评价");
        }

        return convertView;
    }

    @SuppressLint("SetTextI18n")
    private void setCouponsBeanView(List<CouponsBean> coupons, ViewHolder holder) {
        if (coupons == null || coupons.isEmpty()) {
            return;
        }

        // 只取前两个数据
        CouponsBean bean = coupons.get(0);
        holder.tvCommonCouponName.setText(bean.getCouponName());
        holder.tvCommonFavorablePrice.setText("-￥" + bean.getCouponPrice());

        if (coupons.size() > 1) {
            bean = coupons.get(1);
            holder.tvCompanyCouponName.setText(bean.getCouponName());
            holder.tvCompanyFavorablePrice.setText("-￥" + bean.getCouponPrice());
        }
    }

    private void setGoodsView(List<CommoditiesBean> commoditiesBeans, ViewHolder holder) {
        if (commoditiesBeans == null || commoditiesBeans.isEmpty()) {
            return;
        }

        final CustomAdapter<CommoditiesBean> commoditiesBeanAdapter = new CustomAdapter<>(commoditiesBeans);
        commoditiesBeanAdapter.setLayoutView(new LayoutView() {
            @SuppressLint("SetTextI18n")
            @Override
            public View setView(int position, View convertView, ViewGroup parent) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_goods_info, null);
                ImageView goodsIcon = convertView.findViewById(R.id.iv_goods_icon);
                TextView goodsName = convertView.findViewById(R.id.tv_name);
                TextView goodsPrice = convertView.findViewById(R.id.tv_normal_price);

                CommoditiesBean bean = commoditiesBeanAdapter.getItem(position);
                Glide.with(getContext())
                     .load(bean.getCommodityImg())
                     .into(goodsIcon);
                goodsName.setText(bean.getCommodityName());
                goodsPrice.setText("￥" + bean.getPrice());

                return convertView;
            }
        });

        holder.lvWashCarService.setAdapter(commoditiesBeanAdapter);
    }

    @Override
    public void success(int serviceId) {
        getMyConsume();
    }

    class ViewHolder {
        @BindView(R.id.iv_consume_company_icon)
        ImageView ivConsumeCompanyIcon;
        @BindView(R.id.tv_consume_company_name)
        TextView tvConsumeCompanyName;
        @BindView(R.id.tv_service_time)
        TextView tvServiceTime;
        @BindView(R.id.tv_wash_car_name)
        TextView tvWashCarName;
        @BindView(R.id.tv_normal_price)
        TextView tvNormalPrice;
        @BindView(R.id.tv_common_coupon_name)
        TextView tvCommonCouponName;
        @BindView(R.id.tv_common_favorable_price)
        TextView tvCommonFavorablePrice;
        @BindView(R.id.tv_company_coupon_name)
        TextView tvCompanyCouponName;
        @BindView(R.id.tv_company_favorable_price)
        TextView tvCompanyFavorablePrice;
        @BindView(R.id.lv_wash_car_service)
        NoScrollListView lvWashCarService;
        @BindView(R.id.tv_total_price)
        TextView tvTotalPrice;
        @BindView(R.id.button_evaluation)
        Button buttonEvaluation;

        private int serviceId;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            buttonEvaluation.setOnClickListener(v -> new CommitEvaluationDialog(serviceId, MyCustomDialog.this, getContext()).show());
        }

        void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }
    }


}
