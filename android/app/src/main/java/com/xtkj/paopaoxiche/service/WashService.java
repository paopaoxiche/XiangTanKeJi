package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WashService {


    @GET("wash/getWashInfo")
    Call<LoginBean> getWashInfo();



    /**
     * @param lng
     * @param lat
     * @param count
     * @param showAll 是否显示未营业的洗车场（0 - 否，1 - 是）
     * @param priceLimit 价格限制
     * */
    @GET("wash/getNearbyWashServiceList")
    Call<WashServicesBean> getNearbyWashServiceList(@Query("lng") Double lng, @Query("lat")Double lat, @Query("count")int count, @Query("showAll")int showAll, @Query("priceLimit")float priceLimit);


    @GET("wash/getRecommendCommodity")
    Call<WashShopBean> getRecommendCommodity(@Query("washId") String id,@Query("count") int count);

}
