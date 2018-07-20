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

;
public class RetrofitClient {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private static Retrofit mRetrofit ;

    private RetrofitClient() {

    }

    public static Retrofit newInstence(String url) {
        mRetrofit = null;

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json ")
                                .header("token", "fafd097c-0a29-11e8-85e2-02420a2a0003")
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                });
        OkHttpClient client = httpClient.build() ;//初始化一个client,不然retrofit会自己默认添加一个

        mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)//添加一个client,不然retrofit会自己默认添加一个
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit;
    }
}
