package com.god.economics.crawllers;

import com.god.economics.crawllers.instagram.LoginConfig;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * created By gOD on 12/20/2019 2:16 PM
 */

public class Reqs {


    public static String getReq(String url) throws IOException {
        OkHttpClient client =  new OkHttpClient.Builder()
                .connectTimeout(18, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("cookie",LoginConfig.cookie)
                .build();

        try (
                Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static String post(String url, String data) throws IOException {

        


        OkHttpClient client =  new OkHttpClient.Builder()
                .connectTimeout(18, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("cookie",LoginConfig.cookie)
                .build();

        try (
                Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


}
