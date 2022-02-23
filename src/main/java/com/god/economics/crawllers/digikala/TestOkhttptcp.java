package com.god.economics.crawllers.digikala;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TestOkhttptcp {


    public static void main(String[] args) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://google.com")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
             response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
