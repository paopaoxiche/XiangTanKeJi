package com.xtkj.paopaoxiche.service;

import com.xtkj.paopaoxiche.bean.NoDataBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FeedBackService {

    @FormUrlEncoded
    @POST("feedback/submit")
    Call<NoDataBean> submitFeedback(@Field("systemType") String systemType, @Field("device") String device, @Field("versionCode") String versionCode,
                                    @Field("systemVersion") String systemVersion, @Field("content") String content, @Field("contactInformation") String contactInformation);
}
