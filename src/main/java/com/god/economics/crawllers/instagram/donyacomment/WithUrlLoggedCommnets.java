package com.god.economics.crawllers.instagram.donyacomment;

import com.god.economics.crawllers.instagram.models.InstaUserComments;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Optional;

/**
 * created By gOD on 7/10/2020 9:33 AM
 */


public class WithUrlLoggedCommnets {
    public static void main(UserCommentedRepository commentedRepository, String shortcode, String cok, String... args) throws IOException {

        String resp = processNew(null, args[0], cok);
        JSONObject jsonObject = new JSONObject(resp);

        JSONObject root = ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("data")).get("shortcode_media"))
                .get("edge_media_to_parent_comment"));

        JSONArray edges = (JSONArray) root.get("edges");

        JSONObject page_info = (JSONObject) root.get("page_info");
        boolean has = (boolean) page_info.get("has_next_page");

        String end_cursor = (String) page_info
                .get("end_cursor");
        proccessEdges(commentedRepository, edges);

        while (true) {
//                end_cursor = URLEncoder.encode(end_cursor);
            JSONObject endcurserjosn = new JSONObject(end_cursor);
            String v = "";
            try {
                String cached_comments_cursor = (String) endcurserjosn.get("cached_comments_cursor");

                String bifilter_token = (String) endcurserjosn.get("bifilter_token");
                v = String.format("{\"shortcode\":\"%s\",\"first\":120,\"after\":\"{\\\"cached_comments_cursor\\\": \\\"%s\\\", \\\"bifilter_token\\\": \\\"%s\\\"}\"}", shortcode, cached_comments_cursor, bifilter_token);

            } catch (org.json.JSONException e) {
                String bifilter_token = (String) endcurserjosn.get("bifilter_token");
                v = String.format("{\"shortcode\":\"%s\",\"first\":120,\"after\":\"{\\\"bifilter_token\\\": \\\"%s\\\"}\"}", shortcode, bifilter_token);


            }


            System.out.println(v);
            //%7B%22shortcode%22%3A%22CCcNKIcnJds%22%2C%22first%22%3A12%2C%22after%22%3A%22%7B%5C%22cached_comments_cursor%5C%22%3A+%5C%2217864411524938100%5C%22%2C+%5C%22bifilter_token%5C%22%3A+%5C%22KFYBDgDIACAAGAAQABAACAAIAP___2_b--_f_u____P_9f976z_7__v__-98O5_YfG6ilpnnr3__Xz_v-47f_2e____a7-39-___f-f___O223c2FwCRAAA%3D%5C%22%7D%22%7D


            String ev = URLEncoder.encode(v);//first 1 i see
            System.out.println(ev);
            String jing = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=" + ev;

            resp = processNew(page_info, jing, cok);
            jsonObject = new JSONObject(resp);

            root = ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("data")).get("shortcode_media"))
                    .get("edge_media_to_parent_comment"));

            edges = (JSONArray) root.get("edges");

            page_info = (JSONObject) root.get("page_info");
            has = (boolean) page_info.get("has_next_page");

            end_cursor = (String) page_info
                    .get("end_cursor");
            proccessEdges(commentedRepository, edges);
            System.out.println();

            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//             StreamSupport.stream(edges.spliterator(), true)
//             .forEach(o -> ((JSONObject) o).get("node"));


    }

    static public void proccessEdges(UserCommentedRepository commentedRepository, JSONArray edges) {

        System.err.println(edges.length());
        for (int i = 0; i < edges.length(); i++) {
            JSONObject jsonObject = edges.getJSONObject(i);
            String username = (String) ((JSONObject) ((JSONObject) jsonObject
                    .get("node"))
                    .get("owner"))
                    .get("username");
            String userid = (String) ((JSONObject) ((JSONObject) jsonObject
                    .get("node"))
                    .get("owner"))
                    .get("id");
            String text = (String) ((JSONObject) jsonObject
                    .get("node"))
                    .get("text");
            Integer created_at = (Integer) ((JSONObject) jsonObject.get("node")).get("created_at");
            java.util.Date time = new java.util.Date((long) created_at * 1000);

            Optional<InstaUserComments> userCommentsOptional = commentedRepository.findByUserid(userid);
//            OptionalConsumer<Optional<InstaUserComments>> of = OptionalConsumer
//                    .of(instaUserComments -> {
//                        InstaUserComments instaUserComments1 = instaUserComments.get();
//
//                    }, () -> {
//
//                    });

            if (userCommentsOptional.isPresent()) {
                InstaUserComments instaUserComments = userCommentsOptional.get();
                instaUserComments.getComments().add(text);
                commentedRepository.save(instaUserComments);
            } else {
                commentedRepository.save(new InstaUserComments()
                        .addCommnet(text)
                        .setUserid(userid)
                        .setUsername(username));
            }

            System.out.println(username + "   " + text + " " + time);
        }

    }


    public static String processNew(JSONObject page_info, String uri, String cok) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

//        String uri = "https://www.instagram.com/p/"+shortcode+"/";

        HttpGet httpPost = new HttpGet(uri);

//        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("referer", "https://www.instagram.com/p/B7nf91_hkaQ/");
        httpPost.setHeader("x-csrftoken", "k1brA3VGgl4NxvMa2hVggm0KHMmsQNoA");
        httpPost.setHeader("x-ig-app-id", "936619743392459");
        httpPost.setHeader("x-ig-www-claim", "hmac.AR050a6T1x8GV3ajRljbbHZ8PdDvHeGf92e5aat3GEOxYby_\n");
        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie", cok);

        CloseableHttpResponse response = client.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        response.getEntity().getContentLength();  //it should not be 0

        System.out.println(uri);

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();


    }


    public static String buystock(String uri, String cockie , String jsonBody) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

//        String uri = "https://www.instagram.com/p/"+shortcode+"/";

        HttpPost httpPost = new HttpPost(uri);

//        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("content-type", "application/json");
        httpPost.setHeader("cookie",cockie);
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("origin", "https://onlineplus.mofidonline.com");
        httpPost.setHeader("referer", "https://onlineplus.mofidonline.com/Home/Default/page-1");
        httpPost.setHeader("sec-fetch-dest", "empty");
        httpPost.setHeader("sec-fetch-mode", "cors");
        httpPost.setHeader("sec-fetch-site", "same-origin");
        httpPost.setEntity(new StringEntity(jsonBody));
        CloseableHttpResponse response = client.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        response.getEntity().getContentLength();  //it should not be 0
        System.out.println(uri);

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }

}