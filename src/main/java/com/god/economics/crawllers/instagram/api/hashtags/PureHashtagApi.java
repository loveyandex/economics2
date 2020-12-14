package com.god.economics.crawllers.instagram.api.hashtags;

import com.god.economics.crawllers.Reqs;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * created By gOD on 12/15/2020 1:11 AM
 */

public class PureHashtagApi {

    private String tag;

    private String first;
    private String next;

    private boolean has_next_page;
    private String end_cursor;

    public static void main(String[] args) {

        PureHashtagApi tagprocceesing = new PureHashtagApi()
                .first("مزون")
                .processFirstResponse();


        while (tagprocceesing.has_next_page) {
            tagprocceesing.next().processNextResponse();

        }


    }


    public PureHashtagApi first(String tag) {
        this.tag = tag;
        String url = String.format("https://www.instagram.com/explore/tags/%s/?__a=1", tag);
        try {
            this.first = Reqs.getReq(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public PureHashtagApi processNextResponse() {

        JSONObject jsonObject = new JSONObject(this.next);

        JSONObject jsonObject1 =
                ((JSONObject) jsonObject.get("data"));

        JSONObject hashtag = (JSONObject) jsonObject1.get("hashtag");
        JSONArray postedges = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("edges");
        JSONObject page_info = (JSONObject) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("page_info");


        JSONArray toppostedges = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_top_posts"))
                .get("edges");


        this.has_next_page = (boolean) page_info.get("has_next_page");
        this.end_cursor = (String) page_info.get("end_cursor");

        System.out.println(end_cursor);

        return this;
    }

    public PureHashtagApi processFirstResponse() {


        JSONObject jsonObject = new JSONObject(this.first);

        JSONObject jsonObject1 =
                ((JSONObject) jsonObject.get("graphql"));

        JSONObject hashtag = (JSONObject) jsonObject1.get("hashtag");
        JSONArray postedges = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("edges");
        JSONObject page_info = (JSONObject) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("page_info");


        JSONArray toppostedges = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_top_posts"))
                .get("edges");


        this.has_next_page = (boolean) page_info.get("has_next_page");
        this.end_cursor = (String) page_info.get("end_cursor");

        System.out.println(end_cursor);

        return this;
    }


    public PureHashtagApi next() {

        // QVFCOFdYY1pnNWxYTm1MSGVIT1VaOXUyakRiTW5KTGtKc2xBUmJ3T3pBRUkwZFpHMDFSc0lQSXI2cnFmaHlRcEJwYkU1UGZVblJqZ3YzSnJEOTkxUWdpRg==
        String par = String.format("{\"tag_name\":\"%s\", \"first\":10, \"after\":\"%s\"}", this.tag, this.end_cursor);
        String ev = URLEncoder.encode(par);//first 1 i see

        String url = "https://www.instagram.com/graphql/query/?query_hash=9b498c08113f1e09617a1703c22b2f32&variables=" + ev;

        try {
            this.next = Reqs.getReq(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;

    }


}
