package com.god.economics.crawllers.instagram.donyacomment;

import com.god.economics.crawllers.instagram.models.InstaUserComments;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Optional;

/**
 * created By gOD on 7/10/2020 9:33 AM
 */

//@Component
public class LoggedCommnets {

    public static void main(String[] args) throws IOException {
        main(null, "CRwCnwkLzwG",
                "mid=YHRHbwALAAEKf0-5tAO5z5eOw_eR; ig_did=10F7C4EF-0BA7-457A-BE09-0220E2D50FA7; ds_user_id=46965647003; csrftoken=W4kU14cX1tVmVpn5QfT5oYx6nRv9VxFA; sessionid=46965647003%3Al0vUwbM2EAO6QI%3A5; shbid=\"18799\\05446965647003\\0541659020956:01f7034a205db8e535fea0338cd2ff50bb0e744a67c05a352c7dfcf5aec19df74ba89a50\"; shbts=\"1627484956\\05446965647003\\0541659020956:01f72aa8199d1c43f4d4d33c3d2507ca41922f418a3e64dc5e0f0a3fee3bbefc9d8d6a9c\"; rur=\"VLL\\05446965647003\\0541659034408:01f79837f0e43f32935c27d8303b63afef255c670d9045b3536bc1a3f9dbb9cc34449b7c\"");


    }


    public static void main(UserCommentedRepository userCommentedRepository, String shortcode, String cookie) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();


        String uri = "https://www.instagram.com/p/" + shortcode + "/";

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
        httpPost.setHeader("cookie", cookie);

        CloseableHttpResponse response = client.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        response.getEntity().getContentLength();  //it should not be 0

        if (statusCode == 200) {

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


            System.out.println("finalResult " + sb.toString());
            Document document = Jsoup.parse(sb.toString());

            String script = document.getElementsByTag("script").get(14).html().split("window.__additionalDataLoaded\\('/p/" + shortcode + "/',")[1].split("\\);")[0];
            JSONObject jsonObject = new JSONObject(script);

            JSONObject root = (JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("graphql")).get("shortcode_media")).get("edge_media_to_parent_comment");

            JSONObject page_info = (JSONObject) root.get("page_info");
            boolean has = (boolean) page_info.get("has_next_page");

            String end_cursor = (String) page_info
                    .get("end_cursor");
            JSONArray edges = (JSONArray) root.get("edges");

            proccessEdges(userCommentedRepository,edges, new HashSet<>());

            client.close();

            while (true) {
//                end_cursor = URLEncoder.encode(end_cursor);
                JSONObject endcurserjosn = new JSONObject(end_cursor);
                String v = "";
                try {
                    String cached_comments_cursor = (String) endcurserjosn.get("cached_comments_cursor");

                    String bifilter_token = (String) endcurserjosn.get("bifilter_token");
                    v = String.format("{\"shortcode\":\"%s\",\"first\":120,\"after\":\"{\\\"cached_comments_cursor\\\": \\\"%s\\\", \\\"bifilter_token\\\": \\\"%s\\\"}\"}", shortcode, cached_comments_cursor, bifilter_token);

                }catch (org.json.JSONException e)
                {
                    String bifilter_token = (String) endcurserjosn.get("bifilter_token");
                    v = String.format("{\"shortcode\":\"%s\",\"first\":120,\"after\":\"{\\\"bifilter_token\\\": \\\"%s\\\"}\"}", shortcode, bifilter_token);


                }


                System.out.println(v);
                //%7B%22shortcode%22%3A%22CCcNKIcnJds%22%2C%22first%22%3A12%2C%22after%22%3A%22%7B%5C%22cached_comments_cursor%5C%22%3A+%5C%2217864411524938100%5C%22%2C+%5C%22bifilter_token%5C%22%3A+%5C%22KFYBDgDIACAAGAAQABAACAAIAP___2_b--_f_u____P_9f976z_7__v__-98O5_YfG6ilpnnr3__Xz_v-47f_2e____a7-39-___f-f___O223c2FwCRAAA%3D%5C%22%7D%22%7D


                String ev = URLEncoder.encode(v);//first 1 i see
                System.out.println(ev);
                String jing = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=" + ev;

                String resp = processNew(page_info, jing, cookie);
                jsonObject = new JSONObject(resp);

                root = ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("data")).get("shortcode_media"))
                        .get("edge_media_to_parent_comment"));

                edges = (JSONArray) root.get("edges");

                page_info = (JSONObject) root.get("page_info");
                has = (boolean) page_info.get("has_next_page");

                end_cursor = (String) page_info
                        .get("end_cursor");
                proccessEdges(userCommentedRepository,edges, new HashSet<>());
                System.out.println();
            }
//             StreamSupport.stream(edges.spliterator(), true)
//             .forEach(o -> ((JSONObject) o).get("node"));


        } else {
            System.err.println("fucked req");

        }


    }

    static public void proccessEdges(UserCommentedRepository userCommentedRepository, JSONArray edges, HashSet<String> usernames) {

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

//            Optional<InstaUserComments> userCommentsOptional = userCommentedRepository.findByUserid(userid);
//            OptionalConsumer<Optional<InstaUserComments>> of = OptionalConsumer
//                    .of(instaUserComments -> {
//                        InstaUserComments instaUserComments1 = instaUserComments.get();
//
//                    }, () -> {
//
//                    });

//            if (userCommentsOptional.isPresent()) {
//                InstaUserComments instaUserComments = userCommentsOptional.get();
//                instaUserComments.getComments().add(text);
//                userCommentedRepository.save(instaUserComments);
//            }else {
//                userCommentedRepository.save(new InstaUserComments()
//                        .addCommnet(text)
//                        .setUserid(userid)
//                        .setUsername(username));
//            }

            System.out.println(username + "   " + text + " " + time);
        }

    }

    public static String processNew(JSONObject page_info, String uri, String cookie) throws IOException {
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
        httpPost.setHeader("cookie", cookie);

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