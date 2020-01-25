package com.god.economics.crawllers.instagram.comment;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class UserDataAndPage {


    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "http://tsetmc.com/";


        OkHttpClient client = new OkHttpClient();
        String url = "https://www.instagram.com/a.m.satin/?__a=1";


        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);
            Object graphql = jsonObject.get("graphql");
            String[] split = ((String) ((JSONObject) (((JSONObject) graphql).get("user")))
                    .get("biography"))

                    .split("۰۹");
            String rxternalurl = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                    .get("external_url");

            String full_name = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                    .get("full_name");

            int edge_followed_by = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                    .get("edge_followed_by")).get("count"));
            int edge_follow = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                    .get("edge_follow")).get("count"));


            System.out.println(string);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
