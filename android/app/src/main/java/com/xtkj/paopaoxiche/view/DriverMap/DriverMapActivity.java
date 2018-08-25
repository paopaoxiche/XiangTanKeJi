package com.xtkj.paopaoxiche.view.DriverMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.application.MyLocation;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.contract.IDriverMapContract;
import com.xtkj.paopaoxiche.model.DriverMapModel;
import com.xtkj.paopaoxiche.presenter.DriverMapPresenterImpl;
import com.xtkj.paopaoxiche.utils.BitmapUtil;
import com.xtkj.paopaoxiche.utils.DensityUtil;


public class DriverMapActivity extends BaseActivity implements IDriverMapContract.IDriverMapView, AMap.OnMyLocationChangeListener, AMap.OnCameraChangeListener, RouteSearch.OnRouteSearchListener {

    RecyclerView mRecyclerView;
    WashServiceAdapter washServiceAdapter;
    MapView mMapView = null;
    ImageButton backButton;
    AMap aMap;
    RouteSearch mRouteSearch;
    DriveRouteResult mDriveRouteResult;
    AMap.InfoWindowAdapter infoWindowAdapter;

    private IDriverMapContract.IDriverMapPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_map);

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
        mRecyclerView = (RecyclerView)findViewById(R.id.wash_yard_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        backButton = findViewById(R.id.back_button);

        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
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
                if(infoWindow == null) {
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
                WashServicesBean.DataBean  data = (WashServicesBean.DataBean) marker.getObject();
                price.setText(String.format("￥%s", data.getPrice()));
                name.setText(data.getName());
                if(data.getBusinessStatus()== AppConstant.STATE_CLOSED){
                    img.setImageResource(R.drawable.img_close);
                    img.setBackgroundColor(Color.rgb(183,196,203));
                }else if(data.getBusinessStatus()== AppConstant.STATE_STOPPING){
                    img.setImageResource(R.drawable.img_suspend);
                    img.setBackgroundColor(Color.rgb(248,155,10));
                }else if(data.getBusinessStatus()== AppConstant.STATE_OPENING){
                    img.setImageResource(R.drawable.img_operation);
                    img.setBackgroundColor(Color.rgb(17,176,242));
                }
            }

        };
        aMap.setInfoWindowAdapter(infoWindowAdapter);

        mRouteSearch = new RouteSearch(this);
        mRouteSearch.setRouteSearchListener(this);

        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker.getPosition()));
                return false;
            }
        };
        aMap.setOnMarkerClickListener(markerClickListener);
    }

    @Override
    protected void initValues() {
        washServiceAdapter = new WashServiceAdapter(DriverMapModel.getInstance().getWashServicesBean(),this);
        mRecyclerView.setAdapter(washServiceAdapter);
    }

    @Override
    protected void initListeners() {
        backButton.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
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
    public void upDateService() {
        washServiceAdapter.notifyDataSetChanged();

        ViewGroup.LayoutParams lp = mRecyclerView.getLayoutParams();
        WashServicesBean washServicesBean = DriverMapModel.getInstance().getWashServicesBean();
        //list高度自适应，最大两个item的高度
        if (washServicesBean.getData().size() > 2) {
            lp.height = DensityUtil.dip2px(this,250);
        } else {
            lp.height = DensityUtil.dip2px(this,125 * washServicesBean.getData().size());
        }
        mRecyclerView.setLayoutParams(lp);
        for(int i = 0 ; i < washServicesBean.getData().size() ; i ++ ){
            int finalI = i;
            Glide.with(this).load(washServicesBean.getData().get(i).getImage()).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    Bitmap bitmap = BitmapUtil.getZoomImage(BitmapUtil.drawableToBitmap(resource),40,40);
                    bitmap = BitmapUtil.ToRoundBitmap(bitmap);
                    Bitmap bitmap2 = BitmapUtil.getZoomImage(BitmapUtil.drawableToBitmap( getResources().getDrawable(R.drawable.anchor)),80,80);
                    bitmap = BitmapUtil.mergeBitmap(bitmap2,bitmap,20,10);

                    Marker marker = aMap.addMarker(new MarkerOptions().position(new LatLng(washServicesBean.getData().get(finalI).getLat(),washServicesBean.getData().get(finalI).getLng()))
                            .title(washServicesBean.getData().get(finalI).getName())
                            .snippet(washServicesBean.getData().get(finalI).getDistance() + "m")
                            .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                    marker.setObject(washServicesBean.getData().get(finalI));
                }
            });

        }

    }

    @Override
    public void startNavigation(double j, double w) {

        LatLonPoint mStartLatlng = new LatLonPoint(Double.parseDouble(MyLocation.lat), Double.parseDouble(MyLocation.lng));
        LatLonPoint mEndLatlng = new LatLonPoint(w, j);
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartLatlng, mEndLatlng);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault, null,
                null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
        mRouteSearch.calculateDriveRouteAsyn(query);
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
        presenter.updateLocation(cameraPosition.target.longitude,cameraPosition.target.latitude);
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
                    drivingRouteOverlay.setNodeIconVisibility(false);//隐藏转弯的节点
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
}
