package com.god.economics.crawllers.instagram.login;

import com.github.instagram4j.instagram4j.IGClient;
import com.google.gson.Gson;
import kotlin.Pair;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * created By gOD on 12/13/2020 11:33 PM
 */

public class V0LoginApi {


    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.instagram.com/accounts/login/")
                .get();


        Element script = document.getElementsByTag("script").get(3);
        String obj = script.html().split("window._sharedData = ")[1].split(";")[0];
        JSONObject jsonObject = new JSONObject(obj);
        String csrftoken = ((JSONObject) jsonObject.get("config")).get("csrf_token").toString();


        CompletableFuture<Pair<Response, String>> responseFuture = new CompletableFuture<>();
        Request.Builder builder = new Request.Builder()
                .url("https://www.instagram.com/accounts/login/ajax/");
        String username = "dddddddd";
        String password = "godisgreat";
        String encpass = String.format("#PWD_INSTAGRAM_BROWSER:0:%d:%s", System.currentTimeMillis() / 1000, password);


        FormBody body = new FormBody.Builder()
                .add("username", username)
                .add("enc_password", encpass)
                .add("queryParams", "")
                .add("optIntoOneTap", "false")
                .build();

        Request request = applyHeaders(builder, csrftoken).post(body).build();


        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String string = response.body().string();
                List<String> headers = response.headers("Set-Cookie");
                new Gson().toJson(headers);

                System.out.println(string);

            }
        });
    }


    static Request.Builder applyHeaders(Request.Builder req, String csrftoken) {
        req.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        req.addHeader("Content-Type", "application/x-www-form-urlencoded");
        req.addHeader("x-csrftoken", csrftoken);

        return req;

    }

}
