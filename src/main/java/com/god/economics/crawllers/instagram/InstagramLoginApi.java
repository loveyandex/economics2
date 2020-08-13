package com.god.economics.crawllers.instagram;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

/**
 * created By gOD on 7/31/2020 9:56 AM
 */

public class InstagramLoginApi {
    public final static String cookie = "ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; shbid=9033; csrftoken=gjIbkNgCQ7klAZKOpBQTmwZjzHi3IAM5; ds_user_id=30299824247; sessionid=30299824247%3AZm6CiDhNYe8wco%3A8; shbts=1596127512.6155002; rur=ASH; urlgen=\"{\\\"5.236.145.154\\\": 58224\\054 \\\"5.239.198.220\\\": 58224\\054 \\\"37.235.48.166\\\": 51290\\054 \\\"2.182.208.43\\\": 58224}:1k1NvZ:vFoWvNrxD88iOWThfI6HJhb2jx0\"";


    public static void main(String[] args) {


        OkHttpClient client = new OkHttpClient();
        String usename = "kyliejenner   ";
        String url = "https://www.instagram.com/accounts/login/ajax/";


        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", "joobinroozin")
                .addFormDataPart("enc_password", "#PWD_INSTAGRAM_BROWSER:10:1597330793:ARtQAAlRG8sojj0FhvScz9lmOwJWrPKXDzo4TsnWB+tBUGUJFXCovykaMpLOdY2yGNVrCPwPGkETEZWA76+RmdjsttQsfNbGoes9JRP1NyBCpzs4XFS1evCY2F1Jpxyje7d9aqY7GFlMOLdv4Mld")
                .addFormDataPart("queryParams", "{}")
                .addFormDataPart("optIntoOneTap", "false")
                .build();

        RequestBody formBody = new FormBody.Builder()
                .add("username", "joobinroozin")
                .add("enc_password", "#PWD_INSTAGRAM_BROWSER:10:1597330793:ARtQAAlRG8sojj0FhvScz9lmOwJWrPKXDzo4TsnWB+tBUGUJFXCovykaMpLOdY2yGNVrCPwPGkETEZWA76+RmdjsttQsfNbGoes9JRP1NyBCpzs4XFS1evCY2F1Jpxyje7d9aqY7GFlMOLdv4Mld")
                .add("queryParams", "{}")
                .add("optIntoOneTap", "false")
                .build();


        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("x-csrftoken", "jQGAJdQRwj1w2Ybbxe4vMtvnny34Ra5m")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.125 Safari/537.36")
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String cookie = "";
            String csrf = "";
            for (String header : response.headers("set-cookie")) {
                if (header.contains(LoginConfig.CookieParamsForSendingFollow.csrftoken)) {
                    cookie = cookie + " " + header.split(";")[0]+";";
                    csrf = header.split(";")[0];
                }
                if (header.contains(LoginConfig.CookieParamsForSendingFollow.sessionid))
                    cookie = cookie + " " + header.split(";")[0] + ";";
                if (header.contains(LoginConfig.CookieParamsForSendingFollow.mid))
                    cookie = cookie + " " + header.split(";")[0] + ";";
                if (header.contains(LoginConfig.CookieParamsForSendingFollow.ig_did))
                    cookie = cookie + " " + header.split(";")[0] + ";";
                if (header.contains(LoginConfig.CookieParamsForSendingFollow.shbid))
                    cookie = cookie + " " + header.split(";")[0] + ";";
                if (header.contains(LoginConfig.CookieParamsForSendingFollow.shbts))
                    cookie = cookie + " " + header.split(";")[0] + ";";
                if (header.contains(LoginConfig.CookieParamsForSendingFollow.rur))
                    cookie = cookie + " " + header.split(";")[0] + ";";
            }

            System.out.println(cookie);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
