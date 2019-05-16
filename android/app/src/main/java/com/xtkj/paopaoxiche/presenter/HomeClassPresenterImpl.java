package com.xtkj.paopaoxiche.presenter;

import com.xtkj.paopaoxiche.bean.ClassificationCommodityBean;
import com.xtkj.paopaoxiche.contract.IHomeClassContract;
import com.xtkj.paopaoxiche.model.HomeClassModel;

public class HomeClassPresenterImpl implements IHomeClassContract.IHomeClassPresenter,HomeClassModel.HomeClassListener{


    IHomeClassContract.IHomeClassView homeClassView;


    public HomeClassPresenterImpl(IHomeClassContract.IHomeClassView view) {
        homeClassView = view;
        homeClassView.setPresenter(this);
    }


    @Override
    public void onCreate() {
        HomeClassModel.getInstance().addListener(this);
        HomeClassModel.getInstance().getClassificationCommodity();
    }

    @Override
    public void onDestroy() {
        HomeClassModel.getInstance().removeListener(this);
    }

    @Override
    public void getClassificationCommoditySuccess(ClassificationCommodityBean classificationCommodityBean) {
        homeClassView.updateList();
    }

    @Override
    public void getClassificationCommodityFail() {

    }
}
