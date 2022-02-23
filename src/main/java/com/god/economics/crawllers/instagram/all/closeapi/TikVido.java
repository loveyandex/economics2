package com.god.economics.crawllers.instagram.all.closeapi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * @author Abolfazl
 */
public interface TikVido {

    @GET("/video/tos/useast2a/tos-useast2a-ve-0068c002/f9a33b3129a54f6688d84dbe662acc04/?a=1988&br=2466&bt=1233&cd=0%7C0%7C1&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&expire=1638067357&ft=wUyFfFF3kag3-I&l=202111272042260102230841630E71C65B&lr=tiktok_m&mime_type=video_mp4&net=0&pl=0&policy=3&qs=0&rc=amR4aTM6ZjtqODMzNzczM0ApOjxnN2Y7aTw2NzRnZGVlZmdmMWtucjRfa2NgLS1kMTZzczYzMC1iLzE0NjMyNC8xMi06Yw%3D%3D&signature=a92600a748c75b67767e51156753739d&tk=7031829931550540805&vl=&vr=")
    Call<ResponseBody> video( );
}
