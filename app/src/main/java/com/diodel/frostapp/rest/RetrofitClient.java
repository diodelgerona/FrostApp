package com.diodel.frostapp.rest;

import android.text.TextUtils;

import com.diodel.frostapp.BuildConfig;
import com.diodel.frostapp.util.AppConstants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String TAG = "RetrofitClient";
    private static Retrofit retrofit = null;
    private  static String authToken;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(50, TimeUnit.SECONDS);
            builder.connectTimeout(50, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }
                authToken = Credentials.basic(AppConstants.CLIENT_ID, "");

            builder.addInterceptor(new Interceptor() {
                @Override public Response intercept(Chain chain) throws IOException {

                    okhttp3.Request request = chain.request();
                    Headers headers = request.headers().newBuilder().add(AppConstants.AUTHORIZATION, authToken).build();
                    request = request.newBuilder().headers(headers).build();

                    return chain.proceed(request);
                }
            });
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .build();

        }
        return retrofit;
    }
}
