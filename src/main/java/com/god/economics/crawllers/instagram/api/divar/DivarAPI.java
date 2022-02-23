package com.god.economics.crawllers.instagram.api.divar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Abolfazl
 */
public class DivarAPI {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"json_schema\":{\"category\":{\"value\":\"real-estate\"},\"user_type\":{\"value\":\"مشاور املاک\"}}\r\n,\"last-post-date\":1641118446916667\r\n}                 ");
        Request request = new Request.Builder()
                .url("https://api.divar.ir/v8/search/1/real-estate")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

            ResponseBody responseBody = response.body();
            String responseData = responseBody.string();
            JSONObject jsonObject = new JSONObject(responseData);


            System.out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
