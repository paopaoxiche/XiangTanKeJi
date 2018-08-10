package com.xtkj.paopaoxiche.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private static Retrofit mRetrofit ;

    public static Retrofit newInstance(String url) {
        OkHttpClient client = clientBuilder(null) ;//初始化一个client,不然retrofit会自己默认添加一个
        return retrofitBuilder(url,client);
    }

    public static Retrofit newInstance(String url, String authentication) {
        OkHttpClient client = clientBuilder(authentication) ;//初始化一个client,不然retrofit会自己默认添加一个
        return retrofitBuilder(url,client);
    }

    private static OkHttpClient clientBuilder(String authentication){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();
                        Request request ;
                        if(authentication==null){
                            request = original.newBuilder()
                                    .header("Content-Type", "application/json ")
                                    .method(original.method(), original.body())
                                    .build();
                        }else {
                            request = original.newBuilder()
                                    .header("Content-Type", "application/json ")
                                    .header("authentication",authentication)
                                    .method(original.method(), original.body())
                                    .build();
                        }
                        return chain.proceed(request);
                    }
                });

        return httpClient.build();
    }

    private static Retrofit retrofitBuilder(String url,OkHttpClient client){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)//添加一个client,不然retrofit会自己默认添加一个
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit;
    }

    static class ServiceInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            return response;
        }
    }
}
