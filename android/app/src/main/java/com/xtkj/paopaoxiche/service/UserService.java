package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.DemoBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    /**
     * @param phone
     * @param code
     * @param type
     * */
    @GET("user/login")
    Call<DemoBean> Login(@Query("phone") String phone,@Query("code") String code,@Query("type") String type ) ;


    /**
     * @param id
     * */
    @GET("user/info")
    Call<DemoBean> Login(@Query("id") String id ) ;


    /**
     * @param demoBean
     * */
    @POST("user/update")
    Call<DemoBean> Update(@Body DemoBean demoBean);


}
