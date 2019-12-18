package com.god.economics.crawllers.stocks.tsetmc;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class Test {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String url = "http://tsetmc.com/tsev2/data/instinfofast.aspx?i=46348559193224090&c=27+";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] items = string.split(";");

            String[] firstData = items[0].split(",");


            String moamele = firstData[0+2];
            String payani = firstData[1+2];
            String avalin = firstData[2+2];
            String dirooz = firstData[3+2];
            String bazerooz1 = firstData[4+2];
            String bazerooz2 = firstData[5+2];
            String tedadMoamelat= firstData[6+2];
            String volumeOFMoamelat= firstData[7+2];
            String valueOfMoamelat= firstData[8+2];
            String whatthiszero= firstData[9+2];
            String date= firstData[10+2];
            String timehrminsec= firstData[11+2];


            String[] moamelats = items[2].split(",");




            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
