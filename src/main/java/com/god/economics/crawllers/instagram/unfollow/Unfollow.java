package com.god.economics.crawllers.instagram.unfollow;

import com.god.economics.crawllers.instagram.LoginConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * created By gOD on 2/17/2020 10:36 PM
 */

public class Unfollow {
    public static void main(String[] args) throws IOException {

        String[] ids = {"29703930020", "29703930020"};

        String id = ids[(int) (Math.random() * 1000000) % 2];
        id = "38081432117";
        System.out.println(id);

        CloseableHttpClient client = HttpClients.createDefault();
//        String id = "8916622827";// last post
//        id = "6875751076";// username: "ryhwne_mi" full_name: "ＲＥＹＨＡＮＥ🌙🌸️💫"
//        id = "305851563";// username: reza golzar
        //TODO NEED TO BE AUTHENTICATED WITH COOCKIES
        int number = 24;
        String par = String.format("{\"id\":\"%s\",\"include_reel\":true,\"fetch_mutual\":false,\"first\":%d}", id, number);
        String ev = URLEncoder.encode(par);//first 1 i see

        String url = "https://www.instagram.com/graphql/query/?query_hash=d04b0a864b4b54837c0d870b0e77e076&variables=" + ev;
        HttpGet httpPost = new HttpGet(url);

        httpPost.setHeader("x-csrftoken", "jYhVUQB2JqJfoTM5konL1rnurIJ96ruI");
        httpPost.setHeader("x-ig-app-id", "936619743392459");
        httpPost.setHeader("x-ig-www-claim", "hmac.AR2cugIZgXDMS7OrwO9szfy3SNejWHppB1sM8E8aoTPOrImh");
        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie", "ig_cb=1; ig_did=D80D9A22-AE7C-4738-AE68-2170E3B7ABCB; mid=X3VrVAALAAGbmHg-JBNj1CCd4116; csrftoken=jYhVUQB2JqJfoTM5konL1rnurIJ96ruI; ds_user_id=38081432117; sessionid=38081432117%3ADgWBxO5I8JBmkk%3A27; shbid=13311; shbts=1601840044.436691; rur=FRC;");

        CloseableHttpResponse response = client.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {

            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 6522728);
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


            JSONObject jsonObject = new JSONObject(sb.toString());

            JSONArray postedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_follow"))
                    .get("edges");


            String end_cursor = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_follow")).get("page_info")).get("end_cursor");

            boolean has_next_page = (boolean) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_follow")).get("page_info")).get("has_next_page");


            for (int i = 0; i < postedges.length(); i++) {
                System.out.println(i);
                JSONObject node = (JSONObject) postedges.getJSONObject(i).get("node");
                String followingid = (String) node.get("id");
                if (!unfollow(followingid)) {
                    try {
                        Thread.sleep(1000 * 60 * 4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    unfollow(followingid);
                }
                try {
                    Thread.sleep(1000 * 1 * 4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();


        }
    }


    private static boolean unfollow(String id) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        String uri = "https://www.instagram.com/web/friendships/" + id + "/unfollow/";

        HttpPost httpPost = new HttpPost(uri);


        httpPost.setEntity(null);
        httpPost.setHeader("x-csrftoken", LoginConfig.csrftoken);
        httpPost.setHeader("x-ig-app-id", LoginConfig.igappid);
        httpPost.setHeader("x-ig-www-claim", LoginConfig.IGCLAIM);
//        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie", LoginConfig.cookie);


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
            return true;
        } else {
            System.err.println("fucked req");
            return false;

        }
    }
}
