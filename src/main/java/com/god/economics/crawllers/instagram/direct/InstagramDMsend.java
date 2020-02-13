package com.god.economics.crawllers.instagram.direct;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

public class InstagramDMsend {


    public static void main(String[] args) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        String id = "8916622827";// last post
        id = "6875751076";// username: "ryhwne_mi" full_name: "ＲＥＹＨＡＮＥ🌙🌸️💫"
        id = "8664552700";// username: "ryhwne_mi" full_name: "ＲＥＹＨＡＮＥ🌙🌸️💫"
        String uri = "https://i.instagram.com/api/v1/direct_v2/threads/broadcast/text/";

        HttpPost httpPost = new HttpPost(uri);

//        "csrftoken", "XXX"); // replace XXX with the values used when generating signature
//        "device_id", "android-XXX"); // replace XXX with the values used when generating signature
        int timestamp = (int) (System.currentTimeMillis() / 1000);

        String message = "salam kami hastesh";
        String s = "_uuid=" + timestamp + "&recipient_users=" + "[[" + id + "]]" + "&client_context=" + timestamp + "&text=" + message;


        ; // message to customize
        StringEntity entity = new StringEntity(URLEncoder.encode(s));

        httpPost.setEntity(entity);

//        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("referer", "https://www.instagram.com/p/B8eo_9spuKeov9DRMME-2k80D1cKOTPXc0iBiU0/");
        httpPost.setHeader("x-csrftoken", "1QTZjKVbrqUf3JgbacyrGrF74zDVQVBW");
        httpPost.setHeader("x-ig-app-id", "936619743392459");
        httpPost.setHeader("x-ig-www-claim", "hmac.AR050a6T1x8GV3ajRljbbHZ8PdDvHeGf92e5aat3GEOxYby_\n");
        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie", "ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; ds_user_id=11378716565; shbid=4534; shbts=1581533256.9314542; rur=VLL; csrftoken=1QTZjKVbrqUf3JgbacyrGrF74zDVQVBW; sessionid=11378716565%3AuPc9eReA2ScGTy%3A13; urlgen=\"{\\\"77.104.120.73\\\": 42337\\054 \\\"212.80.12.73\\\": 44889\\054 \\\"194.225.108.73\\\": 59794}:1j22Ty:ftY9d2XeO4OnXS9Gx6HL98BlzHA\"");

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
