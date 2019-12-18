package com.god.economics.crawllers.stocks.tgju;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * created By gOD on 12/14/2019 9:12 PM
 */

@RestController
public class TgjuBourse {

    @GetMapping("/god")
    public String error() throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://call.tgju.org/stocks/all.json";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }
    @GetMapping("/mod")
    public String s() throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://call2.tgju.org/ajax.json";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }


}