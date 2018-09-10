package com.xtkj.paopaoxiche.view.WashService;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.alipay.sdk.app.PayTask;
import com.bingo.wxpay.Constants;
import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.bean.CreateConsumeBean;
import com.xtkj.paopaoxiche.bean.MyCouponListBean;
import com.xtkj.paopaoxiche.bean.PostWashServiceBean;
import com.xtkj.paopaoxiche.bean.SellingServicesBean;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.contract.IWashServiceContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.presenter.WashServicePresenterImpl;
import com.xtkj.paopaoxiche.service.CarOwnerService;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xtkj.paopaoxiche.utils.DensityUtil;

public class WashServiceActivity extends BaseActivity implements IWashServiceContract.IWashServiceView, IWXAPIEventHandler, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.service_items)
    LinearLayout serviceItems;
    @BindView(R.id.shop_list)
    LinearLayout shopList;
    @BindView(R.id.pay_button)
    Button payButton;
    @BindView(R.id.pay_radio_group)
    RadioGroup payRadioGroup;


    private IWXAPI api;
    private List<MyCouponListBean.DataBean> couponList = new ArrayList<>();

    IWashServiceContract.IWashServicePresenter presenter;
    int washId;

    Set<WashCommodityBean.DataBean> goodsBeanList = new HashSet<>();

    private PostWashServiceBean postWashServiceBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_service);
        ButterKnife.bind(this);
        Intent i = getIntent();
        washId = i.getIntExtra("washId", 0);

        new WashServicePresenterImpl(this);
        presenter.onCreate();
        initViews();
        initValues();
        initListeners();
    }

    private void buildServiceLayout(SellingServicesBean.DataBean d, int index) {
        LinearLayout linearLayout = (LinearLayout) View.inflate(this, R.layout.item_wash_service_details, null);
        TextView service_name = linearLayout.findViewById(R.id.service_name);
        service_name.setText(d.getName());
        TextView describe = linearLayout.findViewById(R.id.describe);
        describe.setText(d.getDescribe());
        TextView old_price = linearLayout.findViewById(R.id.old_price);

        old_price.setText(String.format("￥%s", d.getPrice()));
        old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        TextView price = linearLayout.findViewById(R.id.price);
        price.setText(String.format("￥%s", d.getPrice()));

        RadioButton radio = linearLayout.findViewById(R.id.radio);

        radio.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                int count = serviceItems.getChildCount();
                for (int j = 0; j < count; j++) {
                    if (index == j) {
                        postWashServiceBean.setWashServiceId(d.getId());
                        double max = 0;
                        for (MyCouponListBean.DataBean dataBean : couponList) {
                            if (Double.valueOf(d.getPrice()) > Double.valueOf(dataBean.getPrice()) && Double.valueOf(dataBean.getPrice()) > max) {
                                max = Double.valueOf(dataBean.getPrice());
                                postWashServiceBean.setCouponId(dataBean.getId());
                            }
                        }
                        if (max > 0) {
                            price.setText(String.format("￥%s - ￥%s", d.getPrice(), String.valueOf(max)));
                        }
                        continue;
                    }
                    RadioButton r = serviceItems.getChildAt(j).findViewById(R.id.radio);
                    r.setChecked(false);
                }
            }
        });

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 16, 0, 16);
        serviceItems.addView(linearLayout, layoutParams);
    }

    private void buildGoodsLayout(WashCommodityBean.DataBean dataBean, int index) {
        RelativeLayout linearLayout = (RelativeLayout) View.inflate(this, R.layout.item_wash_service_shop, null);

        TextView shop_name = linearLayout.findViewById(R.id.shop_name);
        shop_name.setText(dataBean.getName());

        TextView describeTextView = linearLayout.findViewById(R.id.describe_text_view);
        describeTextView.setText(String.format("%s", dataBean.getDescribe()));

        TextView price = linearLayout.findViewById(R.id.price);
        price.setText(String.format("￥%s", dataBean.getCurrentPrice()));
        CheckBox radio = linearLayout.findViewById(R.id.radio);

        ImageView goodsImageView = linearLayout.findViewById(R.id.shop_img);
        Glide.with(this)
                .load(dataBean.getImage())
                .into(goodsImageView);
        radio.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                goodsBeanList.add(dataBean);
            } else {
                goodsBeanList.remove(dataBean);
            }
            String goodsIds = new String("");
            boolean isFirst = true;
            for (WashCommodityBean.DataBean data : goodsBeanList) {
                if (isFirst) {
                    goodsIds = goodsIds + data.getId();
                    isFirst = false;
                } else {
                    goodsIds = goodsIds + "," + data.getId();
                }
            }
            postWashServiceBean.setCommoditys(goodsIds);
        });
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 75));
        layoutParams.setMargins(0, 16, 0, 16);
        shopList.addView(linearLayout, layoutParams);
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initValues() {

        getMyCoupons();
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
        api.handleIntent(getIntent(), this);
        postWashServiceBean = new PostWashServiceBean();
        postWashServiceBean.setCommoditys("");
        postWashServiceBean.setCouponId(0);
        postWashServiceBean.setPayType(AppConstant.PAY_WX);
        postWashServiceBean.setWashServiceId(0);

        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getServiceList(washId)
                .enqueue(new Callback<SellingServicesBean>() {
                    @Override
                    public void onResponse(Call<SellingServicesBean> call, Response<SellingServicesBean> response) {
                        if (response.body() == null) return;
                        ArrayList<SellingServicesBean.DataBean> dataBeans = (ArrayList<SellingServicesBean.DataBean>) response.body().getData();
                        if (dataBeans == null) return;
                        for (int i = 0; i < dataBeans.size(); i++) {
                            buildServiceLayout(dataBeans.get(i), i);
                        }
                    }

                    @Override
                    public void onFailure(Call<SellingServicesBean> call, Throwable t) {

                    }
                });

        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getGoodsList(washId, 0, 20)
                .enqueue(new Callback<WashCommodityBean>() {
                    @Override
                    public void onResponse(Call<WashCommodityBean> call, Response<WashCommodityBean> response) {
                        if (response.body() == null) return;
                        ArrayList<WashCommodityBean.DataBean> dataBeans = (ArrayList<WashCommodityBean.DataBean>) response.body().getData();
                        if (dataBeans == null) return;
                        for (int i = 0; i < dataBeans.size(); i++) {
                            buildGoodsLayout(dataBeans.get(i), i);
                        }
                    }

                    @Override
                    public void onFailure(Call<WashCommodityBean> call, Throwable t) {

                    }
                });


    }

    @Override
    protected void initListeners() {

        backButton.setOnClickListener(view -> finish());
        payButton.setOnClickListener(e -> {
            callPay();
        });
        payRadioGroup.setOnCheckedChangeListener(this);
    }


    private void callPay() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(CarOwnerService.class)
                .createConsume(postWashServiceBean.getWashServiceId(), postWashServiceBean.getCommoditys(), postWashServiceBean.getCouponId(), postWashServiceBean.getPayType())
                .enqueue(new Callback<CreateConsumeBean>() {
                    @Override
                    public void onResponse(Call<CreateConsumeBean> call, Response<CreateConsumeBean> response) {

                        if (response.body() == null || response.body().getCode() != 200) {
                            Toast.makeText(getActivityContext(), "调起支付失败, 请检查选项", Toast.LENGTH_LONG).show();
                            return;
                        }

                        if (postWashServiceBean.getPayType() == 1) {
                            PayReq req = new PayReq();
                            req.appId = response.body().getData().getWxPay().getAppid();
                            req.partnerId = response.body().getData().getWxPay().getPartnerid();
                            req.prepayId = response.body().getData().getWxPay().getPrepayid();
                            req.nonceStr = response.body().getData().getWxPay().getNoncestr();
                            req.timeStamp = response.body().getData().getWxPay().getTimestamp();
                            req.packageValue = response.body().getData().getWxPay().getPackageX();
                            req.sign = response.body().getData().getWxPay().getSign();
                            req.extData = "app data"; // optional
                            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                            boolean isSuccess = api.sendReq(req);
//                        Toast.makeText(getActivityContext(), "调起微信支付", Toast.LENGTH_LONG).show();
                        }
                        if (postWashServiceBean.getPayType() == 2) {

                            new Thread(() -> {
                                PayTask payTask = new PayTask(WashServiceActivity.this);
                                Map<String, String> result = payTask.payV2(response.body().getData().getAliPay(), true);
                            }).start();
//                        Toast.makeText(getActivityContext(), "调起阿里支付成功", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateConsumeBean> call, Throwable t) {
                        Toast.makeText(getActivityContext(), "调起阿里支付失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void setPresenter(IWashServiceContract.IWashServicePresenter iWashServicePresenter) {
        this.presenter = iWashServicePresenter;

    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("支付反馈", "onPayFinish, reqType = " + baseReq.getType());
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.i("支付反馈", "onPayFinish, respType = " + resp.getType());
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.wx_radio_button:
                postWashServiceBean.setPayType(1);
                break;
            case R.id.alipay_radio_button:
                postWashServiceBean.setPayType(2);
                break;
        }
    }

    private void getMyCoupons() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(UserService.class)
                .getMyCoupon()
                .enqueue(new Callback<MyCouponListBean>() {
                    @Override
                    public void onResponse(Call<MyCouponListBean> call, Response<MyCouponListBean> response) {
                        MyCouponListBean bean = response.body();
                        if (bean.getCode() != 200) {
                            Toast.makeText(BaseApplication.getContext(),
                                    "获取个人优惠劵数据失败！", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<MyCouponListBean.DataBean> dataBeans = bean.getData();
                        if (dataBeans != null) {
                            couponList.addAll(dataBeans);
                        }
                    }

                    @Override
                    public void onFailure(Call<MyCouponListBean> call, Throwable t) {

                    }
                });
    }
}
