package com.god.economics.crawllers.stocks.tsetmc;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * created By gOD on 12/14/2019 9:12 PM
 */

@RestController
public class Bourse {

    @GetMapping("/tse")
    public String error() throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://tsetmc.com/tsev2/data/instinfofast.aspx?i=46348559193224090&c=27+";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }


}