package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WashService {


    @GET("wash/getWashInfo")
    Call<LoginBean> getWashInfo();

}
