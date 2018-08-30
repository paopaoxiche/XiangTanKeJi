package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.CarWashInfoBean;
import com.xtkj.paopaoxiche.bean.CouponListBean;
import com.xtkj.paopaoxiche.bean.DemoBean;
import com.xtkj.paopaoxiche.bean.EvaluateListBean;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.bean.MyCouponListBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import java.io.File;

public interface UserService {

       /**
     * @param phone
     * @param code
     * @param type
     * */
    @GET("user/login")
    Call<LoginBean> Login(@Query("phone") String phone, @Query("code") long code, @Query("type") int type ) ;


    /**
     * @param phone
     * */
    @GET("user/getMessageCode")
    Call<NoDataBean> getMessageCode(@Query("phone") String phone) ;



    /**
     * @param id
     * */
    @GET("user/info")
    Call<NoDataBean> Login(@Query("id") String id ) ;

    @FormUrlEncoded
    @POST("user/update")
    Call<NoDataBean> update(@Field("nickname") String nickName);

    /**
     * */
    @Multipart
    @POST("user/update")
    Call<NoDataBean> update(@Part MultipartBody.Part file);

    /**
     * 验证是否为车主TOKEN
     * */
    @GET("user/checkCarOwner")
    Call<NoDataBean> checkCarOwner() ;

    /**
     * 验证是否为洗车场TOKEN
     * */
    @GET("user/checkCarWash")
    Call<NoDataBean> checkCarWash() ;

    @GET("wash/getWashInfo")
    Call<CarWashInfoBean> getCarWashInfo() ;

    @FormUrlEncoded
    @POST("wash/updateStatus")
    Call<NoDataBean> updateBusinessState(@Field("id") int id, @Field("status") String status) ;

    @GET("carOwner/getEvaluateList")
    Call<EvaluateListBean> getMyEvaluateList(@Query("pageIndex") int pageIndex,  @Query("pageSize") int pageSize);

    @GET("carOwner/getMyCoupon")
    Call<MyCouponListBean> getMyCoupon();

    @GET("carOwner/getAllCoupon")
    Call<CouponListBean> getAllCoupons();

    @FormUrlEncoded
    @POST("carOwner/exchangePoint")
    Call<NoDataBean> exchangePoint(@Field("couponId") String couponId);

}
