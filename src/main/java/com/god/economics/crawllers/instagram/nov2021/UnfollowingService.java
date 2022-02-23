package com.god.economics.crawllers.instagram.nov2021;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UnfollowingService {

//  https://i.instagram.com/api/v1/friendships/38081432117/following/?count=12&max_id=12


  @Headers( {
          "accept: */*",
          "accept-language: en-US,en;q=0.9",
          "sec-ch-ua: \"Google Chrome\";v=\"95\", \"Chromium\";v=\"95\", \";Not A Brand\";v=\"99\"",
          "sec-ch-ua-mobile: ?0",
          "sec-ch-ua-platform: \"Windows\"",
          "sec-fetch-dest: empty",
          "sec-fetch-mode: cors",
          "sec-fetch-site: same-site",
          "x-asbd-id: 198387",
          "x-ig-app-id: 936619743392459",
          "x-ig-www-claim: hmac.AR2cugIZgXDMS7OrwO9szfy3SNejWHppB1sM8E8aoTPOrMys",
//          "cookie: mid=YXGxvQALAAFFUttHrNJWaFGE86yn; ig_did=D8631DA0-7675-45A9-A746-4F94A1089BD3; ig_nrcb=1; csrftoken=MP3TcQdgaViAV4pmjZPpvms04qW70hh3; ds_user_id=38081432117; sessionid=38081432117%3A2xKI6YYXnxKdrT%3A22; shbid=\"13311\\05438081432117\\0541667421579:01f74a39057ac7963f243f8a18c37dc461069aeaa21b7c635651b6d0f5bb4058b8e0c83e\"; shbts=\"1635885579\\05438081432117\\0541667421579:01f7100bf4244f22c88b97498061c83f431020e0a4d43c0f9829bfb8c486527a13c0b762\"; rur=\"RVA\\05438081432117\\0541667643486:01f7468b534245c22b97ecac1bceef0500121d2ba0993e16473ef4a058db1ebfe8354f0b\"",
          "Referer: https://www.instagram.com/",
          "Referrer-Policy: strict-origin-when-cross-origin"

          })

  @GET("/api/v1/friendships/{id}/following/")
  Call<FollowersResponse> followings(@Header("cookie") String cookie,
                                    @Path("id") String userid, @Query("count") String count, @Query("max_id") Long max_id);



}