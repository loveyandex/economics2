package com.god.economics.crawllers.instagram.api.hashtags.likerofpost;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface LikersOfPostUsersApi {

    @Headers({
            "x-requested-with: XMLHttpRequest",
//            "x-ig-www-claim: " + LoginConfig.IGCLAIM,
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36"
    })
    @GET("graphql/query/")
    Call<ResponseBody> getLastFollowings(
            @Header("cookie") String cookie,
            @Header("x-csrftoken") String csrftoken,
                                         @QueryMap Map<String, String> options);


}
