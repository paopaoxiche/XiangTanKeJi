package com.xtkj.paopaoxiche.model;

import android.widget.BaseAdapter;
import android.widget.Toast;

import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;

public class GoodsModel {

    private static GoodsModel instance = null;

    private List<GoodsListener> goodsListenerList = new ArrayList<>();

    private List<WashShopBean.DataBean> goodsList = new ArrayList<>();

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

    public List<WashShopBean.DataBean> getGoodsList() {
        return goodsList;
    }

    public void getCarWashGoods(int washId, int pageIndex, int pageSize) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .getGoodsList(washId, pageIndex, pageSize)
                .enqueue(new Callback<WashShopBean>() {
                    @Override
                    public void onResponse(Call<WashShopBean> call, Response<WashShopBean> response) {
                        if (response.body().getCode() != 401) {
                            goodsList = response.body().getData();
                            for (GoodsListener goodsListener : goodsListenerList) {
                                goodsListener.getCarWashGoodsSuccess(response.body());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WashShopBean> call, Throwable t) {

                    }
                });
    }

    public void addGoods(int id, String name, String currentPrice, String originalPrice, String describe, File file) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .addGoods(id, name, currentPrice, originalPrice, describe, file)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body().getCode() != 401) {
                            Toast.makeText(BaseApplication.getContext(), "添加成功，请等待商品审核", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(BaseApplication.getContext(), "添加失败", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(BaseApplication.getContext(), "添加失败", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public static void release() {

    }

}
