package com.xtkj.paopaoxiche.view.DriverMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.overlay.DrivingRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.bingo.wxpay.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.application.MyLocation;
import com.xtkj.paopaoxiche.base.BaseGaodeActivity;
import com.xtkj.paopaoxiche.bean.CreateConsumeBean;
import com.xtkj.paopaoxiche.bean.MyCouponListBean;
import com.xtkj.paopaoxiche.bean.PostWashServiceBean;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.contract.IDriverMapContract;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.model.DriverMapModel;
import com.xtkj.paopaoxiche.model.UserInfo;
import com.xtkj.paopaoxiche.presenter.DriverMapPresenterImpl;
import com.xtkj.paopaoxiche.service.CarOwnerService;
import com.xtkj.paopaoxiche.service.UserService;
import com.xtkj.paopaoxiche.utils.BitmapUtil;
import com.xtkj.paopaoxiche.utils.DensityUtil;
import com.xtkj.paopaoxiche.view.WashService.WashServiceActivity;
import com.xtkj.paopaoxiche.view.view.CommitEvaluationDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DriverMapActivity extends BaseGaodeActivity implements IDriverMapContract.IDriverMapView, AMap.OnMyLocationChangeListener,
        AMap.OnCameraChangeListener, RouteSearch.OnRouteSearchListener, View.OnClickListener, IWXAPIEventHandler, RadioGroup.OnCheckedChangeListener, CommitEvaluationDialog.EvaluateCallback {

    RecyclerView mRecyclerView;
    WashServiceAdapter washServiceAdapter;
    MapView mMapView = null;
    ImageButton backButton;
    AMap aMap;
    RouteSearch mRouteSearch;
    DriveRouteResult mDriveRouteResult;
    AMap.InfoWindowAdapter infoWindowAdapter;
    ImageButton wuyuanxicheImageButton;

    Unbinder unbinder = null;
    @BindView(R.id.car_wash_main_image_view)
    ImageView carWashMainImageView;
    @BindView(R.id.car_wash_address_text_view)
    TextView carWashAddressTextView;
    @BindView(R.id.car_wash_phone_text_view)
    TextView carWashPhoneTextView;
    @BindView(R.id.car_wash_time_text_view)
    TextView carWashTimeTextView;
    //    @BindView(R.id.service_items)
//    LinearLayout serviceItems;
//    @BindView(R.id.shop_list)
//    LinearLayout shopList;
//    @BindView(R.id.pay_radio_group)
//    RadioGroup payRadioGroup;
    @BindView(R.id.pay_button)
    Button payButton;
    @BindView(R.id.wash_car_service_payment_view)
    ScrollView washCarServicePaymentView;
    @BindView(R.id.wash_avatar_image_view)
    ImageView washAvatarImageView;
    @BindView(R.id.wash_name_text_view)
    TextView washNameTextView;
    @BindView(R.id.show_wash_yard_recyclerView)
    LinearLayout showWashYardRecyclerView;
    @BindView(R.id.ll_wash_yard_recyclerView)
    LinearLayout llWashYardRecyclerView;

    private boolean isDetailShow = false;
    private boolean isListShow = true;
    private boolean needToRefrashDetail = false;

    private IWXAPI api;
    private List<MyCouponListBean.DataBean> couponList = new ArrayList<>();

    Set<WashCommodityBean.DataBean> goodsBeanList = new HashSet<>();

    private PostWashServiceBean postWashServiceBean;

    private IDriverMapContract.IDriverMapPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_map);
        unbinder = ButterKnife.bind(this);

        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }

        initViews();
        initValues();
        initListeners();
        new DriverMapPresenterImpl(this);
        presenter.onCreate();

    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.wash_yard_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        backButton = findViewById(R.id.back_button);
        wuyuanxicheImageButton = findViewById(R.id.wuyuanxiche_image_button);

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(14));
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);
        aMap.setOnMyLocationChangeListener(this);
        aMap.setOnCameraChangeListener(this);
        aMap.getUiSettings().setZoomGesturesEnabled(true);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        infoWindowAdapter = new AMap.InfoWindowAdapter() {
            View infoWindow = null;

            @Override
            public View getInfoWindow(Marker marker) {
                if (infoWindow == null) {
                    infoWindow = LayoutInflater.from(getContext()).inflate(
                            R.layout.item_anchor, null);
                }
                render(marker, infoWindow);
                return infoWindow;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }

            public void render(Marker marker, View view) {
                TextView price = view.findViewById(R.id.anchor_price);
                TextView name = view.findViewById(R.id.anchor_name);
                ImageView img = view.findViewById(R.id.anchor_img);
                WashServicesBean.DataBean data = (WashServicesBean.DataBean) marker.getObject();
                presenter.checkedWash(data.getWashId());
                price.setText(String.format("￥%s", data.getPrice()));
                name.setText(data.getName());
                if (data.getBusinessStatus() == AppConstant.STATE_CLOSED) {
                    img.setImageResource(R.drawable.img_close);
                    img.setBackgroundColor(Color.rgb(183, 196, 203));
                } else if (data.getBusinessStatus() == AppConstant.STATE_STOPPING) {
                    img.setImageResource(R.drawable.img_suspend);
                    img.setBackgroundColor(Color.rgb(248, 155, 10));
                } else if (data.getBusinessStatus() == AppConstant.STATE_OPENING) {
                    img.setImageResource(R.drawable.img_operation);
                    img.setBackgroundColor(Color.rgb(17, 176, 242));
                }
                if (isDetailShow) {
                    needToRefrashDetail = true;
                }
            }

        };
        aMap.setInfoWindowAdapter(infoWindowAdapter);

        mRouteSearch = new RouteSearch(this);
        mRouteSearch.setRouteSearchListener(this);

        AMap.OnMarkerClickListener markerClickListener = marker -> {
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker.getPosition()));
            return false;
        };
        aMap.setOnMarkerClickListener(markerClickListener);
    }

    @Override
    protected void initValues() {
        washServiceAdapter = new WashServiceAdapter(DriverMapModel.getInstance().getWashServicesBean(), this);
        mRecyclerView.setAdapter(washServiceAdapter);

        getMyCoupons();
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
        api.registerApp(Constants.APP_ID);
        api.handleIntent(getIntent(), this);
        postWashServiceBean = new PostWashServiceBean();
        postWashServiceBean.setCommoditys("");
        postWashServiceBean.setCouponId(0);
        postWashServiceBean.setPayType(AppConstant.PAY_WX);
        postWashServiceBean.setWashServiceId(0);
    }

    @Override
    protected void initListeners() {

        findViewById(R.id.back_to_list_image_view).setOnClickListener(this);
        findViewById(R.id.back_to_list_text_view).setOnClickListener(this);
        backButton.setOnClickListener(view -> {
            finish();
        });
        wuyuanxicheImageButton.setOnClickListener(this);

        backButton.setOnClickListener(view -> finish());
//        payRadioGroup.setOnCheckedChangeListener(this);

        washServiceAdapter.setListener(new WashServiceAdapter.RequestPaymentListener() {
            @Override
            public void requestPayment(WashServicesBean.DataBean dataBean) {
                requestPaymentPage(dataBean);
                payButton.setOnClickListener(e -> {
                    //callPay();
                    Intent intent = new Intent(getContext(), WashServiceActivity.class);
                    intent.putExtra("washId", dataBean);
                    getContext().startActivity(intent);

                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        unbinder.unbind();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void upDateService(List<WashServicesBean.DataBean> dataBeanList) {
        washServiceAdapter.notifyDataSetChanged();

        ViewGroup.LayoutParams lp = mRecyclerView.getLayoutParams();
        //list高度自适应，最大两个item的高度
        if (dataBeanList.size() > 2) {
            lp.height = DensityUtil.dip2px(this, 250);
        } else {
            lp.height = DensityUtil.dip2px(this, 125 * dataBeanList.size());
        }
        mRecyclerView.setLayoutParams(lp);
        for (int i = 0; i < dataBeanList.size(); i++) {
            int finalI = i;
            Glide.with(this).load(dataBeanList.get(i).getImage()).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    Bitmap bitmap = BitmapUtil.getZoomImage(BitmapUtil.drawableToBitmap(resource), 40, 40);
                    bitmap = BitmapUtil.ToRoundBitmap(bitmap);
                    Bitmap bitmap2 = BitmapUtil.getZoomImage(BitmapUtil.drawableToBitmap(getResources().getDrawable(R.drawable.anchor)), 80, 80);
                    bitmap = BitmapUtil.mergeBitmap(bitmap2, bitmap, 20, 10);

                    Marker marker = aMap.addMarker(new MarkerOptions().position(new LatLng(dataBeanList.get(finalI).getLat(), dataBeanList.get(finalI).getLng()))
                            .title(dataBeanList.get(finalI).getName())
                            .snippet(dataBeanList.get(finalI).getDistance() + "m")
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                    marker.setObject(dataBeanList.get(finalI));
                }
            });
        }
        if (needToRefrashDetail) {
            needToRefrashDetail = false;
            if (dataBeanList.size() > 0) {
                requestPaymentPage(dataBeanList.get(0));
            }
        }
    }

    @Override
    public void startNavigation(double j, double w) {

        LatLonPoint mStartLatlng = new LatLonPoint(Double.parseDouble(MyLocation.lat), Double.parseDouble(MyLocation.lng));
        LatLonPoint mEndLatlng = new LatLonPoint(w, j);
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartLatlng, mEndLatlng);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DRIVING_SINGLE_DEFAULT, null,
                null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路

        mRouteSearch.calculateDriveRouteAsyn(query);


    }

    @Override
    public void changeCamera(int washId, double lon, double lat) {
        mMapView.getMap().moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(lat, lon)));
        presenter.checkedWash(washId);
        for (Marker marker : mMapView.getMap().getMapScreenMarkers()) {
            if (marker.getObject() != null
                    && marker.getObject() instanceof WashServicesBean.DataBean) {
                if (((WashServicesBean.DataBean) marker.getObject()).getWashId() == washId) {
                    marker.showInfoWindow();
                    break;
                }
            }
        }

    }

    @Override
    public void setPresenter(IDriverMapContract.IDriverMapPresenter iDriverMapPresenter) {
        presenter = iDriverMapPresenter;
    }


    @Override
    public void onMyLocationChange(Location location) {
//        presenter.updateLocation(location.getLongitude(),location.getLatitude());
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
//        presenter.updateLocation(cameraPosition.target.longitude,cameraPosition.target.latitude);
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        presenter.updateLocation(cameraPosition.target.longitude, cameraPosition.target.latitude);
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {
        aMap.clear();
        if (i == 1000) {
            if (driveRouteResult != null && driveRouteResult.getPaths() != null) {
                if (driveRouteResult.getPaths().size() > 0) {
                    mDriveRouteResult = driveRouteResult;
                    final DrivePath drivePath = mDriveRouteResult.getPaths()
                            .get(0);
                    DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(this, aMap, drivePath, driveRouteResult.getStartPos(),
                            driveRouteResult.getTargetPos());
                    drivingRouteOverlay.removeFromMap();
                    drivingRouteOverlay.setNodeIconVisibility(true);//隐藏转弯的节点
                    drivingRouteOverlay.addToMap();
                    drivingRouteOverlay.zoomToSpan();
                }
            }
        }
    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.wuyuanxiche_image_button) {
            if (UserInfo.isIsCheckWuYuanXiChe()) {
                wuyuanxicheImageButton.setImageResource(R.drawable.btn_radio_nor);
                UserInfo.setIsCheckWuYuanXiChe(false);
                presenter.checkWuYuanXiChe();
            } else {
                wuyuanxicheImageButton.setImageResource(R.drawable.btn_radio_true);
                UserInfo.setIsCheckWuYuanXiChe(true);
                presenter.checkWuYuanXiChe();
            }
        } else if (v.getId() == R.id.back_to_list_image_view
                || v.getId() == R.id.back_to_list_text_view) {
            if (isDetailShow) {
                isDetailShow = false;
                llWashYardRecyclerView.setVisibility(View.VISIBLE);
                washCarServicePaymentView.setVisibility(View.GONE);
            }
        }
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        api.handleIntent(intent, this);
//    }
//
//    private void buildServiceLayout(SellingServicesBean.DataBean d, int index) {
//        LinearLayout linearLayout = (LinearLayout) View.inflate(this, R.layout.item_wash_service_details, null);
//        TextView service_name = linearLayout.findViewById(R.id.service_name);
//        service_name.setText(d.getName());
//        TextView describe = linearLayout.findViewById(R.id.describe);
//        describe.setText(d.getDescribe());
//        TextView old_price = linearLayout.findViewById(R.id.old_price);
//
//        old_price.setText(String.format("￥%s", d.getPrice()));
//        old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//
//        TextView price = linearLayout.findViewById(R.id.price);
//        price.setText(String.format("￥%s", d.getPrice()));
//
//        RadioButton radio = linearLayout.findViewById(R.id.radio);
//
//        radio.setOnCheckedChangeListener((compoundButton, b) -> {
//            if (b) {
//                int count = serviceItems.getChildCount();
//                for (int j = 0; j < count; j++) {
//                    if (index == j) {
//                        postWashServiceBean.setWashServiceId(d.getId());
//                        double max = 0;
//                        for (MyCouponListBean.DataBean dataBean : couponList) {
//                            if (Double.valueOf(d.getPrice()) > Double.valueOf(dataBean.getPrice()) && Double.valueOf(dataBean.getPrice()) > max) {
//                                max = Double.valueOf(dataBean.getPrice());
//                                postWashServiceBean.setCouponId(dataBean.getId());
//                            }
//                        }
//                        if (max > 0) {
//                            price.setText(String.format("￥%s - ￥%s", d.getPrice(), String.valueOf(max)));
//                        } else {
//                            postWashServiceBean.setCouponId(0);
//                        }
//                        continue;
//                    }
//                    RadioButton r = serviceItems.getChildAt(j).findViewById(R.id.radio);
//                    r.setChecked(false);
//                }
//            }
//        });
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(0, 16, 0, 16);
//        serviceItems.addView(linearLayout, layoutParams);
//    }
//
//    private void buildGoodsLayout(WashCommodityBean.DataBean dataBean, int index) {
//        RelativeLayout linearLayout = (RelativeLayout) View.inflate(this, R.layout.item_wash_service_shop, null);
//
//        TextView shop_name = linearLayout.findViewById(R.id.shop_name);
//        shop_name.setText(dataBean.getName());
//
//        TextView price = linearLayout.findViewById(R.id.price);
//        price.setText(String.format("￥%s", dataBean.getCurrentPrice()));
//        CheckBox radio = linearLayout.findViewById(R.id.radio);
//
//        ImageView goodsImageView = linearLayout.findViewById(R.id.shop_img);
//        Glide.with(this)
//                .load(dataBean.getImage())
//                .into(goodsImageView);
//        radio.setOnCheckedChangeListener((compoundButton, b) -> {
//            if (b) {
//                goodsBeanList.add(dataBean);
//            } else {
//                goodsBeanList.remove(dataBean);
//            }
//            String goodsIds = new String("");
//            boolean isFirst = true;
//            for (WashCommodityBean.DataBean data : goodsBeanList) {
//                if (isFirst) {
//                    goodsIds = goodsIds + data.getId();
//                    isFirst = false;
//                } else {
//                    goodsIds = goodsIds + "," + data.getId();
//                }
//            }
//            postWashServiceBean.setCommoditys(goodsIds);
//        });
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 75));
//        layoutParams.setMargins(0, 16, 0, 16);
//        shopList.addView(linearLayout, layoutParams);
//    }

    private void requestPaymentPage(WashServicesBean.DataBean dataBean) {

        isDetailShow = true;

        llWashYardRecyclerView.setVisibility(View.GONE);
        washCarServicePaymentView.setVisibility(View.VISIBLE);

        washNameTextView.setText(dataBean.getName());
        Glide.with(this).load(dataBean.getImage()).into(washAvatarImageView);
        carWashTimeTextView.setText(dataBean.getWorktime());
        carWashAddressTextView.setText(dataBean.getAddress());
        Glide.with(this).load(dataBean.getFacadeImg()).into(carWashMainImageView);

//        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
//                .create(WashService.class)
//                .getServiceList(dataBean.getWashId())
//                .enqueue(new Callback<SellingServicesBean>() {
//                    @Override
//                    public void onResponse(Call<SellingServicesBean> call, Response<SellingServicesBean> response) {
//                        if (response.body() == null) return;
//                        ArrayList<SellingServicesBean.DataBean> dataBeans = (ArrayList<SellingServicesBean.DataBean>) response.body().getData();
//                        if (dataBeans == null) return;
//                        for (int i = 0; i < dataBeans.size(); i++) {
//                            buildServiceLayout(dataBeans.get(i), i);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SellingServicesBean> call, Throwable t) {
//
//                    }
//                });
//
//        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
//                .create(WashService.class)
//                .getGoodsList(dataBean.getWashId(), 0, 20)
//                .enqueue(new Callback<WashCommodityBean>() {
//                    @Override
//                    public void onResponse(Call<WashCommodityBean> call, Response<WashCommodityBean> response) {
//                        if (response.body() == null) return;
//                        ArrayList<WashCommodityBean.DataBean> dataBeans = (ArrayList<WashCommodityBean.DataBean>) response.body().getData();
//                        if (dataBeans == null) return;
//                        for (int i = 0; i < dataBeans.size(); i++) {
//                            buildGoodsLayout(dataBeans.get(i), i);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<WashCommodityBean> call, Throwable t) {
//
//                    }
//                });
    }


    private void callPay() {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(CarOwnerService.class)
                .createConsume(postWashServiceBean.getWashServiceId(), postWashServiceBean.getCommoditys(), postWashServiceBean.getCouponId(), postWashServiceBean.getPayType())
                .enqueue(new Callback<CreateConsumeBean>() {
                    @Override
                    public void onResponse(Call<CreateConsumeBean> call, Response<CreateConsumeBean> response) {

                        if (response.body() == null || response.body().getCode() != 200) {
                            Toast.makeText(DriverMapActivity.this, "调起支付失败, 请检查选项", Toast.LENGTH_LONG).show();
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
                        }
                        if (postWashServiceBean.getPayType() == 2) {

                            new Thread(() -> {
                                PayTask payTask = new PayTask(DriverMapActivity.this);
                                Map<String, String> result = payTask.payV2(response.body().getData().getAliPay(), true);
                                if (result != null && result.get("resultStatus") != null && result.get("resultStatus").equals("9000")) {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DriverMapActivity.this, "支付成功", Toast.LENGTH_LONG).show();
                                            new CommitEvaluationDialog(postWashServiceBean.getWashServiceId(), (CommitEvaluationDialog.EvaluateCallback) DriverMapActivity.this, DriverMapActivity.this).show();
                                        }
                                    });
                                } else {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(DriverMapActivity.this, "支付失败", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }).start();
//                        Toast.makeText(getActivityContext(), "调起阿里支付成功", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateConsumeBean> call, Throwable t) {
                        Toast.makeText(DriverMapActivity.this, "调起阿里支付失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("支付反馈", "onPayFinish, reqType = " + baseReq.getType());
        //Toast.makeText(this, "openid = " + baseReq.openId, Toast.LENGTH_SHORT).show();
        // new CommitEvaluationDialog(postWashServiceBean.getWashServiceId(), this, this).show();
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                //goToGetMsg();
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                // goToShowMsg((ShowMessageFromWX.Req) req);
                break;
            case ConstantsAPI.COMMAND_LAUNCH_BY_WX:
                Toast.makeText(this, com.bingo.wxpay.R.string.launch_from_wx, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.i("支付反馈", "onPayFinish, respType = " + resp.getType());
        //Toast.makeText(this, "openid = " + resp.openId, Toast.LENGTH_SHORT).show();
        new CommitEvaluationDialog(postWashServiceBean.getWashServiceId(), this, this).show();
        if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            Toast.makeText(this, "code = " + ((SendAuth.Resp) resp).code, Toast.LENGTH_SHORT).show();

        }
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

    @Override
    public void success(int serviceId) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isDetailShow) {
                isDetailShow = false;
                mRecyclerView.setVisibility(View.VISIBLE);
                llWashYardRecyclerView.setVisibility(View.GONE);
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.show_wash_yard_recyclerView)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.show_wash_yard_recyclerView:
                    if(isListShow)
                        mRecyclerView.setVisibility(View.GONE);
                    else
                        mRecyclerView.setVisibility(View.VISIBLE);
                    isListShow = !isListShow;
                break;

        }
    }
}
