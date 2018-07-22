package com.xtkj.paopaoxiche.view.WashCarMap;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.base.BaseActivity;

import java.util.ArrayList;

public class WashCarMapActivity extends BaseActivity {

    Toolbar toolbar;
    RecyclerView mRecyclerView;
    YardsAdapter yardsAdapter;
    MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wash_car_map);

        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        initViews();
        initValues();
        initListeners();

    }

    @Override
    protected void initViews() {
        mRecyclerView = (RecyclerView)findViewById(R.id.wash_yard_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initValues() {
        String[] datas = {"asdasdasd","asdasdasdqwe","asdasdas","asdasdasd","564456","123qsdsa","zxCasdas"};
        yardsAdapter= new YardsAdapter(datas);
        mRecyclerView.setAdapter(yardsAdapter);
    }

    @Override
    protected void initListeners() {

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
}
