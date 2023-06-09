package com.example.wl_softpos_sdk.common.repository.network;


import com.example.wl_softpos_sdk.BuildConfig;
import com.example.wl_softpos_sdk.common.utils.SPManager;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Ganesh on 23/05/17.
 */
public class ApiClient {

    private static final String TAG = "ApiClient";

    private ApiClient() {
        throw new IllegalStateException(TAG);
    }

    private static Retrofit retrofit = null;

    public static ApiInterface getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseURL())
                    .client(getOkkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                    //  .addConverterFactory(new UnwrapConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create())))
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }


    public static ApiEncpInterface getEncClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseURL())
                    .client(getOkkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                    //  .addConverterFactory(new UnwrapConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create())))
                    .build();
        }
        return retrofit.create(ApiEncpInterface.class);
    }

    private static OkHttpClient getOkkHttpClient() {
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add(getBaseURL().replace("https://", "").replace("/uat/public/api/", "").replace("/development/public/api/", "").replace("/api/", ""),
                        getPinningSha256Key())
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .callTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(new HeaderInterceptor());

        if (BuildConfig.DEBUG) {
            builder.interceptors().add(httpLoggingInterceptor());
        }
        return builder.build();
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     * Web service method name
     */

    public static class ApiMethod {


        private ApiMethod() {
            throw new IllegalStateException(TAG);
        }

        public static final String AADHAAR_API = "aadhaarApi";


    }

    /**
     * Created by Ganesh on 19/6/17.
     */

    public static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain)
                throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build();

            Response response = chain.proceed(request);
            if (response.code() != 500 && response.code() != 401) {
                Response.Builder customBuilder = response.newBuilder();
                customBuilder.code(200);
                return customBuilder.build();
            } else if (response.code() == 500) {
                Response.Builder customBuilder = response.newBuilder();
                customBuilder.code(500);
                return customBuilder.build();
            }
            return response;
        }
    }

    private static String getBaseURL() {
        return "/api/";
    }

    private static String getPinningSha256Key() {
        return "dfsdf";
    }
}
