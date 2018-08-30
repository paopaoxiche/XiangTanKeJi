package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.CouponListBean;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;
import com.xtkj.paopaoxiche.bean.RecentWashListBean;
import com.xtkj.paopaoxiche.bean.WashCommodityBean;
import com.xtkj.paopaoxiche.bean.WashServiceListBean;
import com.xtkj.paopaoxiche.bean.WashServicesBean;
import com.xtkj.paopaoxiche.bean.WashShopBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import java.io.File;

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
    Call<WashShopBean> getRecommendCommodity(@Query("lng") String lng,@Query("lat") String lat,@Query("count") int count);


    @GET("wash/getCommodityList")
    Call<WashCommodityBean> getGoodsList(@Query("washId") Integer washId, @Query("pageIndex") Integer pageIndex, @Query("pageSize") int pageSize);

    @Multipart
    @POST("commodity/addCommodity")
    Call<NoDataBean> addGoods(@Part("id") RequestBody id, @Part("name") RequestBody name, @Part("currentPrice") RequestBody currentPrice,
                              @Part("originalPrice") RequestBody originalPrice, @Part("describe") RequestBody describe, @Part MultipartBody.Part commodityImg);

    @Multipart
    @POST("commodity/addCommodity")
    Call<NoDataBean> addGoods(@Part("id") RequestBody id, @Part("name") RequestBody name, @Part("currentPrice") RequestBody currentPrice,
                              @Part("originalPrice") RequestBody originalPrice, @Part("describe") RequestBody describe);

    @GET("wash/getServiceList")
    Call<WashServiceListBean> getWashServviceList(@Query("washId")int washId);

    @FormUrlEncoded
    @POST("wash/publishService")
    Call<NoDataBean> addService(@Field("washId")int washId, @Field("serviceId") int serviceId, @Field("name") String name, @Field("describe") String describe, @Field("price") String price, @Field("carModel") int carModel);

//    @POST("wash/publishService")
//    Call<NoDataBean> addService(@Body int washId, @Body int serviceId, @Body String name, @Body String describe, @Body String price);

//    @Multipart
//    @POST("wash/publishService")
//    Call<NoDataBean> addService(@Part("washId")RequestBody washId, @Part("serviceId") RequestBody serviceId, @Part("name") RequestBody name, @Part("describe") RequestBody describe, @Part("price") RequestBody price);

    @FormUrlEncoded
    @POST("wash/deleteService")
    Call<NoDataBean> deleteWashService(@Field("washId") int washId, @Field("serviceId") int serviceId);

    @FormUrlEncoded
    @POST("commodity/deleteCommodity")
    Call<NoDataBean> deleteGoods(@Field("id") int id);

    @GET("carOwner/getAllCoupon")
    Call<CouponListBean> getAllCoupons();

    @FormUrlEncoded
    @POST("carOwner/exchangePoint")
    Call<NoDataBean> exchangePoint(@Field("couponId") String couponId);

    @GET("wash/getRecentWashList")
    Call<RecentWashListBean> getRecentWashList(@Query("washId") int washId, @Query("count") int count);

    @Multipart
    @POST("wash/registerWash")
    Call<NoDataBean> certification(@Part("phone") RequestBody phone, @Part("name") RequestBody name, @Part("address") RequestBody address,
                              @Part("coordX") RequestBody coordX, @Part("coordY") RequestBody coordY,
                                   @Part MultipartBody.Part license, @Part MultipartBody.Part washCard, @Part MultipartBody.Part idCardPositive, @Part MultipartBody.Part idCardBack);
}
