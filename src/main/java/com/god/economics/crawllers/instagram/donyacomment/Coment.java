package com.god.economics.crawllers.instagram.donyacomment;

import com.god.economics.crawllers.Reqs;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Scanner;

/**
 * created By gOD on 1/25/2020 9:11 PM
 */
@RestController
public class Coment {


    public static void main(String[] args) throws IOException {


        String s = "https://www.instagram.com/p/B8jcHtqJrmx/";
        s = "https://www.instagram.com/p/B_hqd42jk4y/";
        s = "https://www.instagram.com/p/CAp2UliHT_T/";
        s = "https://www.instagram.com/p/CBNLTfdDUvz/";
        s = "https://www.instagram.com/p/CBGCUB1DirX/";
        s = "https://www.instagram.com/p/CBv_312pCou/";
        s = "https://www.instagram.com/p/CCGqmF-jPEB/";
        s = "https://www.instagram.com/p/CCRRlUBJ2Lq/";
        s = "https://www.instagram.com/p/CCVnahgDjFA/";
        s = "https://www.instagram.com/p/CCbAAchjlsS/";
        s = "https://www.instagram.com/p/CCcNKIcnJds/";


//
        Document document = Jsoup.connect(s).get();

        String script = document.getElementsByTag("script").get(4).html().split("window._sharedData = ")[1].split(";")[0];
        JSONObject jsonObject = new JSONObject(script);

        JSONObject mediacommnet = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("entry_data")).get("PostPage"))
                .get(0)).get("graphql"))
                .get("shortcode_media")).get("edge_media_to_parent_comment");

        JSONObject page_info = (JSONObject) mediacommnet.get("page_info");
        boolean has = (boolean) page_info.get("has_next_page");

        String end_cursor = (String) page_info
                .get("end_cursor");
        JSONArray edges = (JSONArray) mediacommnet.get("edges");
        HashSet<String> usernames = new HashSet<>();
        proccessEdges(edges,usernames);
        while (has) {
//            new Scanner(System.in).nextLine();

            String v = String.format("{\"shortcode\":\"B_hqd42jk4y\",\"first\":200,\"after\":\"%s\"}",end_cursor);
            String ev = URLEncoder.encode(v);//first 1 i see

            String jing = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables="+ev;
            String req = Reqs.getReq(jing);
            jsonObject = new JSONObject(req);

            mediacommnet= ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("data")).get("shortcode_media"))
                    .get("edge_media_to_parent_comment"));



            page_info = (JSONObject) mediacommnet.get("page_info");
            has = (boolean) page_info.get("has_next_page");

            end_cursor = (String) page_info
                    .get("end_cursor");
            proccessEdges(edges,usernames);

        }

        System.out.println(script);


    }

    //    @GetMapping("/starthashtag")
//    public static void maine(String[] args) throws IOException {
//        String script = null;
//
//        try {
//            script = first();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JSONObject jsonObject = new JSONObject(script);
//
//        JSONObject mediacommnet = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("entry_data")).get("PostPage"))
//                .get(0)).get("graphql"))
//                .get("shortcode_media")).get("edge_media_to_parent_comment");
//
//        JSONObject page_info = (JSONObject) mediacommnet.get("page_info");
//        boolean has = (boolean) page_info.get("has_next_page");
//
//        String end_cursor = (String) page_info
//                .get("end_cursor");
//        JSONArray edges = (JSONArray) mediacommnet.get("edges");
//
//
//        proccessEdges(postedges, possibleUserIds);
//
//
//        JSONArray toppostedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("entry_data")).get("TagPage")).getJSONObject(0)
//                .get("graphql")).get("hashtag")).get("edge_hashtag_to_top_posts"))
//                .get("edges");
//
//        proccessEdges(toppostedges, possibleUserIds);
//
//        JSONObject page_info = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("entry_data")).get("TagPage")).getJSONObject(0)
//                .get("graphql")).get("hashtag")).get("edge_hashtag_to_media"))
//                .get("page_info");
//
//        boolean has_next_page = (boolean) page_info.get("has_next_page");
//        String end_cursor = (String) page_info.get("end_cursor");
//        /*
//         * {"tag_name":"mezon","first":1,"after":"QVFEc0FiZkNncGJVU0dGQ2czOENzWGlfbFBzZEpyUVZJc1BXN1kxeGpjMm15SFlIUXV4bHNzOWJ6cGt1N204QVNlaVVUQWV2QlYteU1rNzFqUlphS3Y3LQ=="}
//         *  {"tag_name":"mezon","first":10,"after":"QVFBVUlvS1MwOTVaaXYwR01JVURGLU0yYWlYbjFLSUExVnlFYm5rT0lSSDZkNUxHczJMbU5xQWJwMkFkOHU5dWdObDVILVNKQ3dtSWE1YThXRkpsZnMtdQ=="}
//         * */
//        System.out.println(end_cursor);
//
//        while (has_next_page) {
//            String mezon = null;
//            try {
//                mezon = next(tag, end_cursor);
//            } catch (IOException e) {
//                try {
//                    mezon = next(tag, end_cursor);
//                } catch (IOException ex) {
//                    System.err.println(ex);
//
//                }
//            }
////            System.out.println(mezon);
//
//            jsonObject = new JSONObject(mezon);
//
//            postedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
//                    .get("data"))
//                    .get("hashtag")).get("edge_hashtag_to_media"))
//                    .get("edges");
//
//            page_info = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
//                    .get("data"))
//                    .get("hashtag")).get("edge_hashtag_to_media"))
//                    .get("page_info");
//
//            has_next_page = (boolean) page_info.get("has_next_page");
//            end_cursor = (String) page_info.get("end_cursor");
//            proccessEdges(postedges, possibleUserIds);
//            System.out.println(end_cursor);
//
//            FileWriter writer = new FileWriter("ids" + tagid + ".txt", false);
//            for (int i = 0; i < possibleUserIds.size(); i++) {
//                writer.write(possibleUserIds.toArray()[i] + "\n");
//            }
//            writer.flush();
//            writer.close();
//            System.out.println(possibleUserIds.size());
//
//        }
//
//
//        JSONObject variables = new JSONObject();
//        variables.put("shortcode", "B7wmtKhHTgT");
//        variables.put("child_comment_count", 3);
//        variables.put("fetch_comment_count", 40);
//        variables.put("parent_comment_count", 24);
//        variables.put("has_threaded_comments", true);
//
//        String qh = "query_hash=2b0673e0dc4580674a88d426fe00ea90&";
//        String paramstring = variables.toString();
//
//        String paramencode = "variables=" + URLEncoder.encode(paramstring);
//        String apirootpath = "https://www.instagram.com/graphql/query/?";
//
//        String req = null;
//        try {
//            req = Reqs.getReq(apirootpath + qh + paramencode);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(req);
//        String v = "{\"id\":\"11378716565\"}";
//        String ev = URLEncoder.encode(v);
//        String uri = "https://www.instagram.com/graphql/query/?query_hash=0f318e8cfff9cc9ef09f88479ff571fb&variables=" + ev;
//
//        System.out.println(uri);
//        String req1 = null;
//        try {
//            String id = "12276944309";
//            req1 = Reqs.getReq("https://www.instagram.com/graphql/query/?query_hash=c9100bf9110dd6361671f113dd02e7d6&variables=%7B%22user_id%22%3A%22" + id + "%22%2C%22include_chaining%22%3Atrue%2C%22include_reel%22%3Atrue%2C%22include_suggested_users%22%3Afalse%2C%22include_logged_out_extras%22%3Afalse%2C%22include_highlight_reels%22%3Atrue%2C%22include_related_profiles%22%3Afalse%7D");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String resp = req1;
//        System.out.println(resp);
//
//
//    }
//
//
//
//
//    public static String first() throws IOException {
//        String s = "https://www.instagram.com/p/B8grxXdpsod/";
//
//        Document document = Jsoup.connect(s).get();
//
//        String script = document.getElementsByTag("script").get(4).html().split("window._sharedData = ")[1].split(";")[0];
//
//
////        System.out.println(script);
//        return script;
//    }
//
    static public void proccessEdges(JSONArray edges,HashSet<String> usernames ) {

        System.err.println(edges.length());
        for (int i = 0; i < edges.length(); i++) {
            JSONObject jsonObject = edges.getJSONObject(i);
            String username = (String) ((JSONObject) ((JSONObject) jsonObject
                    .get("node"))
                    .get("owner"))
                    .get("username");
            String text = (String)  ((JSONObject) jsonObject
                    .get("node"))
                    .get("text");
            Integer created_at = (Integer) ((JSONObject) jsonObject.get("node")).get("created_at");
            java.util.Date time = new java.util.Date((long) created_at * 1000);

            usernames.add(username);
            System.out.println(username+"   "+text+" "+time);
        }

    }


}
