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
        String usename = "kyliejenner   ";
        String url = "https://www.instagram.com/" + usename + "/?__a=1";


        Request request = new Request.Builder()
                .url(url)
                .header("cookie", "ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; shbid=9033; shbts=1597127951.8850539; csrftoken=V35nxNiZFTUz8LIulvzuq4d8Cpia8Sd6; ds_user_id=30299824247; sessionid=30299824247%3Ah8a85IrRkUlgYI%3A12; rur=ASH; urlgen=\"{\\\"5.239.206.153\\\": 58224\\054 \\\"151.236.27.61\\\": 57169}:1k6DIR:d4nkn9nX5IKHTpJi-4KTrzbvHI4\"")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);

            if (jsonObject.isEmpty())
                System.out.println("empty user");

            Object graphql = jsonObject.get("graphql");

            JSONObject user = (JSONObject) (((JSONObject) graphql).get("user"));
            String bio = ((String) user
                    .get("biography"));

            String rxternalurl = (String) user.get("external_url");

            String full_name = (String) user.get("full_name");

            int edge_followed_by = ((int) ((JSONObject) user.get("edge_followed_by")).get("count"));
            int edge_follow = ((int) ((JSONObject) user.get("edge_follow")).get("count"));


            System.out.println(string);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
