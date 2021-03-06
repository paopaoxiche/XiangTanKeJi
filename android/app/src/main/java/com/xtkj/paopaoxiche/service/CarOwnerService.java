package com.xtkj.paopaoxiche.service;


import com.xtkj.paopaoxiche.bean.CreateConsumeBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CarOwnerService {

    @FormUrlEncoded
    @POST("carOwner/createConsume")
    Call<CreateConsumeBean> createConsume(@Field("washServiceId") int washServiceId,@Field("commoditys") String commoditys,@Field("couponId") int couponId,@Field("payType") int payType);


}
