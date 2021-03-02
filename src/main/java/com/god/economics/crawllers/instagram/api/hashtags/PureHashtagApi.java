package com.god.economics.crawllers.instagram.api.hashtags;

import com.god.economics.crawllers.Reqs;
import com.god.economics.crawllers.instagram.api.hashtags.instamodel.PostNode;
import com.god.economics.crawllers.instagram.api.hashtags.posts.Flow;
import com.google.gson.Gson;
import lombok.val;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * created By gOD on 12/15/2020 1:11 AM
 */

public class PureHashtagApi {

    private String tag;

    private String first;
    private String next;

    private boolean has_next_page;
    private String end_cursor;
    private JSONArray mostRecentposts;
    private JSONArray topposts;
    private ArrayList<String> ignoreshortcodes = new ArrayList<>();



    public static void main(String[] args) {

        new PureHashtagApi()
                .first("مانتو")
                .processFirstResponse()
                .processTopposts()
                .continueHowmuch(2)
//                .continuee()
        ;
    }



    private PureHashtagApi processTopposts() {
        final JSONArray topposts = this.topposts;

        for (int i = 0; i < topposts.length(); i++) {
            JSONObject jsonObject = topposts.getJSONObject(i);
            JSONObject node = (JSONObject) jsonObject
                    .get("node");

            PostNode postNode = new Gson().fromJson(node.toString(), PostNode.class);

            System.out.println(postNode.__typename);

            System.out.println(postNode.id);
            System.out.println("https://instagram.com/p/"+postNode.shortcode+"/");

            if (ignoreshortcodes.contains(postNode.shortcode))
                continue;

            new Flow().run(postNode.shortcode);
            ignoreshortcodes.add(postNode.shortcode);

//            new Thread(() -> {
//            }).start();

            String id = (String) ((JSONObject) node
                    .get("owner"))
                    .get("id");

            int count = (int) ((JSONObject) node
                    .get("edge_liked_by")).get("count");


            System.out.println("edge_liked " + count);
        }




        return this;
    }


    public PureHashtagApi continueHowmuch(int thismuch) {
        int i = 0;
        while (i++ < thismuch) {
            this.next().processNextResponse();
        }
        return this;
    }


    public PureHashtagApi continuee() {
        while (this.keepable()) {
            this.next().processNextResponse();
        }
        return this;
    }


    private boolean keepable() {
        return has_next_page;
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
        this.mostRecentposts = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("edges");
        JSONObject page_info = (JSONObject) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("page_info");


        this.topposts = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_top_posts"))
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
        this.mostRecentposts = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("edges");
        JSONObject page_info = (JSONObject) ((JSONObject) hashtag.get("edge_hashtag_to_media"))
                .get("page_info");
        this.topposts = (JSONArray) ((JSONObject) hashtag.get("edge_hashtag_to_top_posts"))
                .get("edges");

        this.has_next_page = (boolean) page_info.get("has_next_page");
        this.end_cursor = (String) page_info.get("end_cursor");
        System.out.println(end_cursor);
        return this;
    }


    public PureHashtagApi next() {
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
