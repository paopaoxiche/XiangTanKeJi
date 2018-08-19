package com.xtkj.paopaoxiche.model;

import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsModel {

    private static GoodsModel instance = null;

    private List<GoodsListener> goodsListenerList = new ArrayList<>();

    private GoodsModel() {

    }

    public static GoodsModel getInstance() {
        if (instance == null) {
            instance = new GoodsModel();
        }
        return instance;
    }

    public interface GoodsListener {
        void getCarWashGoodsSuccess(WashShopBean washShopBean);
    }

    public void addListener(GoodsListener goodsListener) {
        goodsListenerList.add(goodsListener);
    }

    public void removeListener(GoodsListener goodsListener) {
        goodsListenerList.remove(goodsListener);
    }

    public void getCarWashGoods(int washId, int pageIndex, int pageSize) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getGoodsList(washId, pageIndex, pageSize)
                .enqueue(new Callback<WashShopBean>() {
                    @Override
                    public void onResponse(Call<WashShopBean> call, Response<WashShopBean> response) {
                        for (GoodsListener goodsListener : goodsListenerList) {
                            goodsListener.getCarWashGoodsSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<WashShopBean> call, Throwable t) {

                    }
                });
    }

    public static void release() {

    }

}
