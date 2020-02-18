package com.god.economics.crawllers.instagram.all;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class UserDataAndPage {


    public static void main(String[] args) throws IOException, InterruptedException {
        String host = "http://tsetmc.com/";


        OkHttpClient client = new OkHttpClient();
        String usename = "a.m.satin";
        String url = "https://www.instagram.com/" +usename+ "/?__a=1";


        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);
            Object graphql = jsonObject.get("graphql");

            String bio = ((String) ((JSONObject) (((JSONObject) graphql).get("user")))
                    .get("biography"));

            String[] split = bio.split("۰۹");
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
