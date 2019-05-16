package com.xtkj.paopaoxiche.model;

import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.bean.ClassificationCommodityBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.AdService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeClassModel {

    private static HomeClassModel instance = null;
    private List<HomeClassListener> homeClassListeners = null;

    private ClassificationCommodityBean classificationCommodityBean = null;
    private String type;


    public HomeClassModel(){
        homeClassListeners = new ArrayList<>();
        classificationCommodityBean = new ClassificationCommodityBean();
    }


    public static HomeClassModel getInstance() {
        if(instance == null){
            instance = new HomeClassModel();
        }
        return instance;
    }

    public interface HomeClassListener {
        void getClassificationCommoditySuccess(ClassificationCommodityBean classificationCommodityBean);
        void getClassificationCommodityFail();
    }

    public void addListener(HomeClassListener homeClassListener) {
        homeClassListeners.add(homeClassListener);
    }

    public void removeListener(HomeClassListener homeClassListener) {
        homeClassListeners.remove(homeClassListener);
    }


    public void getClassificationCommodity(){
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(AdService.class)
                .getClassificationCommodity(type)
                .enqueue(new Callback<ClassificationCommodityBean>(){

                    @Override
                    public void onResponse(Call<ClassificationCommodityBean> call, Response<ClassificationCommodityBean> response) {
                        classificationCommodityBean = response.body();
                        for (HomeClassListener homeClassListener : homeClassListeners) {
                            homeClassListener.getClassificationCommoditySuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassificationCommodityBean> call, Throwable t) {
                        for (HomeClassListener homeClassListener : homeClassListeners) {
                            homeClassListener.getClassificationCommodityFail();
                        }
                    }
                });
    }


    public ClassificationCommodityBean getClassificationCommodityBean() {
        return classificationCommodityBean;
    }

    public void setClassificationCommodityBean(ClassificationCommodityBean classificationCommodityBean) {
        this.classificationCommodityBean = classificationCommodityBean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
