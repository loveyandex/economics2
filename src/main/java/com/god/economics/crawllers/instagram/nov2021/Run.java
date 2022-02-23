package com.god.economics.crawllers.instagram.nov2021;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author Abolfazl
 */
public class Run {

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://i.instagram.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UnfollowingService service = retrofit.create(UnfollowingService.class);

        Call<FollowersResponse> followersResponseCall = service.followings(
                "mid=YXGxvQALAAFFUttHrNJWaFGE86yn; ig_did=D8631DA0-7675-45A9-A746-4F94A1089BD3; ig_nrcb=1; csrftoken=MP3TcQdgaViAV4pmjZPpvms04qW70hh3; ds_user_id=38081432117; sessionid=38081432117%3A2xKI6YYXnxKdrT%3A22; shbid=\\\"13311\\\\05438081432117\\\\0541667421579:01f74a39057ac7963f243f8a18c37dc461069aeaa21b7c635651b6d0f5bb4058b8e0c83e\\\"; shbts=\\\"1635885579\\\\05438081432117\\\\0541667421579:01f7100bf4244f22c88b97498061c83f431020e0a4d43c0f9829bfb8c486527a13c0b762\\\"; rur=\\\"RVA\\\\05438081432117\\\\0541667643486:01f7468b534245c22b97ecac1bceef0500121d2ba0993e16473ef4a058db1ebfe8354f0b\\\"\"",
                "38081432117", "120", 0L);

        Response<FollowersResponse> execute = followersResponseCall.execute();

        FollowersResponse body = execute.body();

        System.out.println();




    }
}
