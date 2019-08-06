package com.god.economics.controllers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * created By aMIN on 7/26/2019 1:38 PM
 */

public class Run {

    static OkHttpClient client = new OkHttpClient();

   static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public static void main(String[] args) throws IOException {
        for (int i = 0; i <100; i++) {

            System.err.println(run("https://www.aparat.com/v/NUAMm"));



        }
    }




}
