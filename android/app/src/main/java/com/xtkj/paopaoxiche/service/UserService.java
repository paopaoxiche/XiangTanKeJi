package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.DemoBean;
import com.xtkj.paopaoxiche.bean.LoginBean;
import com.xtkj.paopaoxiche.bean.NoDataBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


    /**
     * */
    @POST("user/update")
    Call<NoDataBean> update(@Body String nickName);

    /**
     * */
    @POST("user/update")
    Call<NoDataBean> update(@Field("file") File file);

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



}
