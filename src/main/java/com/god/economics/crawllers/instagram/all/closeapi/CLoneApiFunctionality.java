package com.god.economics.crawllers.instagram.all.closeapi;

import com.god.economics.crawllers.instagram.LoginConfig;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static com.god.economics.crawllers.instagram.all.closeapi.CloneApi.query_hash;

/**
 * created By gOD on 11/23/2020 4:12 AM
 */

public class CLoneApiFunctionality {

    private String userId;

    public CLoneApiFunctionality(String userId) {

        this.userId = userId;
    }

    public void newEdges(String afterEncode) {
        this.userId = "373148161";// my id
        String par2 = String.format("{\"id\":\"%s\",\"first\":12,\"after\":\"%s\"}", userId, afterEncode);
        String url = "https://www.instagram.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        CloneApi cloneApi = retrofit.create(CloneApi.class);


        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("query_hash", query_hash);
        objectObjectHashMap.put("variables", par2);
        Call<ResponseBody> call = cloneApi.getLastFollowings(LoginConfig.cookie, objectObjectHashMap);

        Response<ResponseBody> execute;
        try {
            execute = call.execute();
            ResponseBody body = execute.body();
            String string = Objects.requireNonNull(body).string();
            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
