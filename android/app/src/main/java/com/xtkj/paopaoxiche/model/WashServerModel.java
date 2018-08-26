package com.xtkj.paopaoxiche.model;

import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.bean.WashServiceListBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WashServerModel {

    List<WashServiceListBean.DataBean> washServerList = new ArrayList<>();

    List<WashServiceListener> washServiceListenerList = new ArrayList<>();

    private static WashServerModel instance = null;

    public static WashServerModel getInstance() {
        if (instance == null) {
            instance = new WashServerModel();
        }
        return instance;
    }

    private WashServerModel() {

    }

    public interface WashServiceListener {
        void getWashServerListSuccess(WashServiceListBean washServiceListBean);
    }

    public void addListener(WashServiceListener washServiceListener) {
        washServiceListenerList.add(washServiceListener);
    }

    public void removeListener(WashServiceListener washServiceListener) {
        washServiceListenerList.remove(washServiceListener);
    }

    public void getWashServerList(int washId, int pageIndex, int pageSize) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getWashServviceList(washId)
                .enqueue(new Callback<WashServiceListBean>() {
                    @Override
                    public void onResponse(Call<WashServiceListBean> call, Response<WashServiceListBean> response) {
                        if (response == null) {
                            return;
                        }
                        if (response.body().getCode() != 401) {
                            washServerList = response.body().getData();
                            for (WashServiceListener washServiceListener : washServiceListenerList) {
                                washServiceListener.getWashServerListSuccess(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WashServiceListBean> call, Throwable t) {

                    }
                });
    }

    public List<WashServiceListBean.DataBean> getWashServerList() {
        return washServerList;
    }

}
