package com.god.economics.crawllers.instagram.all;

import com.god.economics.crawllers.Reqs;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class ShowFollowerOfAuser {


    public static void main(String[] args) throws IOException {
        String next = next(24, "305851563");
        JSONObject jsonObject = new JSONObject(next);
        System.out.println();
    }


    public static String next(int number, String id) throws IOException {
        //TODO NEED TO BE AUTHENTICATED WITH COOCKIES
        String par = String.format("{\"id\":\"%s\",\"include_reel\":true,\"fetch_mutual\":true,\"first\":%d}", id, number);
        String ev = URLEncoder.encode(par);//first 1 i see

        String url = "https://www.instagram.com/graphql/query/?query_hash=c76146de99bb02f6415203be841dd25a&variables=" + ev;

        return Reqs.getReq(url);


    }
}
