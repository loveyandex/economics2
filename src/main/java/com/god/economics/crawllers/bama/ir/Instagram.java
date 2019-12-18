package com.god.economics.crawllers.bama.ir;

import okhttp3.*;

import java.io.IOException;

/**
 * created By gOD on 12/14/2019 4:20 AM
 */

public class Instagram {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        MediaType JSON
                = MediaType.get("application/x-www-form-urlencoded; charset=utf-8");
        String url = "https://www.instagram.com/web/comments/2167025698447090219/add/";
        String json = "comment_text=kingfid3&replied_to_comment_id=";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
