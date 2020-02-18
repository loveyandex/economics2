package com.god.economics.crawllers.instagram.all;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class PnstaPomment {


    public static void main(String[] args) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        String id = "2194939228587455523";
        id = "2227859801112021496";//donya last post
        id = "2197568975716933739";//mahsa_._khanoom_ last post https://www.instagram.com/p/B5_VVcXBuBr/
        id = "2229439841077227618";//mahsa_._khanoom_ last post https://www.instagram.com/p/B7wj7kvgvBi/
        id = "2229310149819146625";//donya last post
        String uri = "https://www.instagram.com/web/comments/" + id + "/add/";

        HttpPost httpPost = new HttpPost(uri);

        String text1 = "javanan bani hashem biaid mara pishe rokhe eshgham bezarid";

        text1 = "مشاوره برای سرمایه گذاری در بورس و بازار های مالی ";
        text1 = "are man bidaram";

        String encode = URLEncoder.encode(text1);
        Long idcomnt = 18126994897055292L;
         idcomnt = 18097081609127139L;
        String json = "comment_text=" + encode + "&replied_to_comment_id=" + idcomnt;
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("referer", "https://www.instagram.com/p/B7nf91_hkaQ/");
        httpPost.setHeader("x-csrftoken", "k1brA3VGgl4NxvMa2hVggm0KHMmsQNoA");
        httpPost.setHeader("x-ig-app-id", "936619743392459");
        httpPost.setHeader("x-ig-www-claim", "hmac.AR050a6T1x8GV3ajRljbbHZ8PdDvHeGf92e5aat3GEOxYby_\n");
        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie", "ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; csrftoken=k1brA3VGgl4NxvMa2hVggm0KHMmsQNoA; shbid=4534; shbts=1579880039.7452745; ds_user_id=11378716565; sessionid=11378716565%3AqsWar2zkpTvpWH%3A13; rur=VLL; urlgen=\"{\\\"212.80.12.73\\\": 44889}:1iv863:5SXMcCjQX682KjP8IXvxXuU9kKs\"");

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
