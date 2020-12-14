package com.god.economics.crawllers.instagram.all;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class InstaUnFollowing {


    public static void main(String[] args) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        String id = "373148161";//donya last post
        String uri = "https://www.instagram.com/web/friendships/" + id + "/unfollow/";

        HttpPost httpPost = new HttpPost(uri);


        httpPost.setEntity(null);

//        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//        httpPost.setHeader("referer", "https://www.instagram.com/p/B7nf91_hkaQ/");
        httpPost.setHeader("x-csrftoken", "S945ujWkCwHvrUnpqEVxqUCDFMwPeAJf");
//        httpPost.setHeader("x-ig-app-id", "936619743392459");
//        httpPost.setHeader("x-ig-www-claim", "hmac.AR050a6T1x8GV3ajRljbbHZ8PdDvHeGf92e5aat3GEOxYby_\n");
//        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
//        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
//        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie",
                " csrftoken=S945ujWkCwHvrUnpqEVxqUCDFMwPeAJf; ig_did=6B1CFB64-F81E-43FC-AB14-05DCE395B80F; rur=NAO; mid=X9afRQALAAE1tGNjA_Soi1S6dlKL; sessionid=38081432117%3AgmQdGWWurMfwRe%3A12;");

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


            client.close();
        } else {
            System.err.println("fucked req");

        }

//
    }
}
