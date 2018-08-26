package com.xtkj.paopaoxiche.model;

import android.widget.Toast;

import com.xtkj.paopaoxiche.application.Authentication;
import com.xtkj.paopaoxiche.application.BaseApplication;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.WashServiceListBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;
import com.xtkj.paopaoxiche.http.ApiField;
import com.xtkj.paopaoxiche.http.RetrofitClient;
import com.xtkj.paopaoxiche.service.WashService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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

    public void addWashService(int washId, int serviceId, String name, String describe, String price) {

        RequestBody washIdBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(washId));
        RequestBody serviceIdBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(serviceId));
        RequestBody nameBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), name);
        RequestBody describeBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), describe);
        RequestBody priceBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), price);

        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .addService(washIdBody, serviceIdBody, nameBody, describeBody, priceBody)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body().getCode() != 401) {
                            Toast.makeText(BaseApplication.getContext(), "添加洗车服务成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BaseApplication.getContext(), "添加洗车服务失败，请重新登录", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(BaseApplication.getContext(), "添加洗车服务失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteWashService(int washId, int serviceId) {
        RetrofitClient.newInstance(ApiField.BASEURL, Authentication.getAuthentication())
                .create(WashService.class)
                .deleteWashService(washId, serviceId)
                .enqueue(new Callback<NoDataBean>() {
                    @Override
                    public void onResponse(Call<NoDataBean> call, Response<NoDataBean> response) {
                        if (response.body().getCode() != 401) {
                            Toast.makeText(BaseApplication.getContext(), "删除洗车服务成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BaseApplication.getContext(), "删除洗车服务失败，请重新登录", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NoDataBean> call, Throwable t) {
                        Toast.makeText(BaseApplication.getContext(), "删除洗车服务失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public List<WashServiceListBean.DataBean> getWashServerList() {
        return washServerList;
    }

}
