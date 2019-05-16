package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.ClassificationCommodityBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  AdService {

    @GET("ad/getClassificationCommodity")
    Call<ClassificationCommodityBean> getClassificationCommodity(@Query("type") String type);

}
