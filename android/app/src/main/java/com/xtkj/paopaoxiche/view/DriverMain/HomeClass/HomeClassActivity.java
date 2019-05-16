package com.xtkj.paopaoxiche.view.DriverMain.HomeClass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xtkj.paopaoxiche.R;
import com.xtkj.paopaoxiche.application.AppConstant;
import com.xtkj.paopaoxiche.base.BaseActivity;
import com.xtkj.paopaoxiche.contract.IHomeClassContract;
import com.xtkj.paopaoxiche.model.HomeClassModel;
import com.xtkj.paopaoxiche.presenter.HomeClassPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeClassActivity extends BaseActivity implements IHomeClassContract.IHomeClassView {

    IHomeClassContract.IHomeClassPresenter homeClassPresenter;

    HomeClassAdapter homeClassAdapter = null;

    @BindView(R.id.back_button)
    ImageView backButton;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.class_recycler)
    RecyclerView classRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initValues();
        initListeners();


        new HomeClassPresenterImpl(this);
        homeClassPresenter.onCreate();

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_home_class);
        ButterKnife.bind(this);
        classRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initValues() {
        Intent intent = getIntent();
        String intentData = intent.getStringExtra("title");
        title.setText(String.format("%s", intentData));
        HomeClassModel.getInstance().setTitle(intentData);
        switch (intentData) {
            case AppConstant.HOME_CLASS_1:
                HomeClassModel.getInstance().setType(AppConstant.HOME_CLASS_1_QUERY);
                break;
            case AppConstant.HOME_CLASS_2:
                HomeClassModel.getInstance().setType(AppConstant.HOME_CLASS_2_QUERY);
                break;
            case AppConstant.HOME_CLASS_3:
                HomeClassModel.getInstance().setType(AppConstant.HOME_CLASS_3_QUERY);
                break;
            case AppConstant.HOME_CLASS_4:
                HomeClassModel.getInstance().setType(AppConstant.HOME_CLASS_4_QUERY);
                break;
            case AppConstant.HOME_CLASS_5:
                HomeClassModel.getInstance().setType(AppConstant.HOME_CLASS_5_QUERY);
                break;
        }
    }

    @Override
    protected void initListeners() {

    }

    @OnClick(R.id.back_button)
    public void onViewClicked() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void updateList() {
        homeClassAdapter = new HomeClassAdapter(HomeClassModel.getInstance().getClassificationCommodityBean(),this);
        classRecycler.setAdapter(homeClassAdapter);
    }

    @Override
    public void setPresenter(IHomeClassContract.IHomeClassPresenter iHomeClassPresenter) {
        this.homeClassPresenter = iHomeClassPresenter;
    }
}
