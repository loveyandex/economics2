package com.god.economics.crawllers.instagram.api.hashtags;

import com.god.economics.crawllers.Reqs;
import com.god.economics.crawllers.instagram.api.hashtags.instamodel.PostNode;
import com.god.economics.crawllers.instagram.api.hashtags.posts.Flow;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * created By gOD on 12/15/2020 1:11 AM
 */

public class PureHashtagApiV2 {

    private String tag;

    private String first;
    private String next;

    private boolean has_next_page;
    private String end_cursor;
    private JSONArray mostRecentposts;
    private JSONArray topposts;
    private ArrayList<String> ignoreshortcodes = new ArrayList<>();
    private String top_max_id;
    private String nextTop;


    public static void main(String[] args) {

        new PureHashtagApiV2()
                .first("مانتو")
                .processFirstResponse()
                .nextTop()
                .processNextTop()
                .processTopposts()
                .continueHowmuch(2)
//                .continuee()
        ;
    }



    private PureHashtagApiV2 processTopposts() {
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


    public PureHashtagApiV2 continueHowmuch(int thismuch) {
        int i = 0;
        while (i++ < thismuch) {
            this.next().processNextResponse();
        }
        return this;
    }


    public PureHashtagApiV2 continuee() {
        while (this.keepable()) {
            this.next().processNextResponse();
        }
        return this;
    }


    private boolean keepable() {
        return has_next_page;
    }


    public PureHashtagApiV2 first(String tag) {
        this.tag = tag;
        String url = String.format("https://www.instagram.com/explore/tags/%s/?__a=1", tag);
        try {
            this.first = Reqs.getReq(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public PureHashtagApiV2 processNextResponse() {

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

    public PureHashtagApiV2 processFirstResponse() {
        JSONObject jsonObject = new JSONObject(this.first);
         JSONObject jsonObject1 =
                ((JSONObject) jsonObject.get("data"));

        JSONObject data =
                ((JSONObject) jsonObject.get("data"));

        JSONObject recent =
                ((JSONObject) data.get("recent"));

        JSONObject top =
                ((JSONObject) data.get("top"));


        Integer next_page =
                ((Integer) recent.get("next_page"));

        Boolean more_available =
                ((Boolean) recent.get("more_available"));

        String next_max_id =
                ((String) recent.get("next_max_id"));
        String Topnext_max_id =
                ((String) top.get("next_max_id"));
        this.top_max_id = Topnext_max_id;


        JSONArray sections =
                ((JSONArray) recent.get("sections"));







        return this;
    }


    public PureHashtagApiV2 next() {
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
    public PureHashtagApiV2 nextTop() {
        String par = String.format("{\"tag_name\":\"%s\", \"first\":10, \"after\":\"%s\"}", this.tag, this.top_max_id);
        String ev = URLEncoder.encode(par);//first 1 i see
        String url = "https://www.instagram.com/graphql/query/?query_hash=9b498c08113f1e09617a1703c22b2f32&variables=" + ev;

        try {
            this.nextTop = Reqs.getReq(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;

    }
    public PureHashtagApiV2 processNextTop() {

        JSONObject jsonObject = new JSONObject(this.nextTop);


        return this;

    }


}
