package com.god.economics.crawllers.instagram.comment;

import com.god.economics.FollowingFollowersRepository;
import com.god.economics.crawllers.instagram.comment.models.InstaUser;
import com.god.economics.crawllers.instagram.comment.models.follow.FollowingFollower;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */
@RestController
public class FindFollowers {
    @Autowired
    private FollowingFollowersRepository followingFollowersRepository;


    @GetMapping("/sendfollowing/{id}")
    public String sendfollowing(@PathVariable String id) throws Exception {


        Optional<FollowingFollower> followingFollowerBy = followingFollowersRepository.findFollowingFollowerBy(id);
        FollowingFollower followingFollower = followingFollowerBy.get();
        List<InstaUser> followers = followingFollower.getFollowers();
        for (InstaUser follower : followers) {
            String followerId = follower.getId();
            String request = sendFollowingRequest(followerId);
            follower.setStatus(request);
        }
        followingFollowersRepository.save(followingFollower);




        return "ok";
    }

    private String sendFollowingRequest(String followerId) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();

        String uri = "https://www.instagram.com/web/friendships/8916622827/" + followerId + "/follow/";

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(null);

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


            System.err.println(sb.toString());
            JSONObject jsonObject = new JSONObject(sb.toString());
            String result = jsonObject.get("result").toString();
            return result;

        } else {
            System.err.println("fucked req");

        }
        return "fucked";
    }

    @GetMapping("/following/{id}")
    public Optional<FollowingFollower> followingFollower(@PathVariable String id) throws Exception {

        return followingFollowersRepository.findFollowingFollowerBy(id);
    }

    @GetMapping("/followingnum/{id}")
    public int followingFollowernum(@PathVariable String id) throws Exception {
        return followingFollowersRepository.findFollowingFollowerBy(id).get().getFollowers().size();
    }


    @GetMapping("/follow/{id}")
    public void main(@PathVariable String id) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
//        String id = "8916622827";// last post
//        id = "6875751076";// username: "ryhwne_mi" full_name: "ＲＥＹＨＡＮＥ🌙🌸️💫"
//        id = "305851563";// username: reza golzar
        //TODO NEED TO BE AUTHENTICATED WITH COOCKIES
        int number = 150;
        String par = String.format("{\"id\":\"%s\",\"include_reel\":true,\"fetch_mutual\":true,\"first\":%d}", id, number);
        String ev = URLEncoder.encode(par);//first 1 i see

        String url = "https://www.instagram.com/graphql/query/?query_hash=c76146de99bb02f6415203be841dd25a&variables=" + ev;
        HttpGet httpPost = new HttpGet(url);


//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("referer", "https://www.instagram.com/p/B7nf91_hkaQ/");
        httpPost.setHeader("x-csrftoken", "k1brA3VGgl4NxvMa2hVggm0KHMmsQNoA");
        httpPost.setHeader("x-ig-app-id", "936619743392459");
        httpPost.setHeader("x-ig-www-claim", "hmac.AR050a6T1x8GV3ajRljbbHZ8PdDvHeGf92e5aat3GEOxYby_\n");
        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie", "ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; csrftoken=k1brA3VGgl4NxvMa2hVggm0KHMmsQNoA; shbid=4534; shbts=1579880039.7452745; ds_user_id=11378716565; sessionid=11378716565%3AqsWar2zkpTvpWH%3A13; rur=VLL; urlgen=\"{\\\"212.80.12.73\\\": 44889}:1iv863:5SXMcCjQX682KjP8IXvxXuU9kKs\"");

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
                    .get("user")).get("edge_followed_by"))
                    .get("edges");

            ArrayList<InstaUser> instaUsers = new ArrayList<>();
            for (int i = 0; i < postedges.length(); i++) {
                JSONObject node = (JSONObject) postedges.getJSONObject(i).get("node");
                boolean is_private = (boolean) node.get("is_private");
                String followingid = (String) node.get("id");
                String username = (String) node.get("username");
                String full_name = (String) node.get("full_name");
                InstaUser instaUser = new InstaUser(followingid, username, full_name, is_private);
                instaUsers.add(instaUser);
            }
            FollowingFollower firstfollowingFollowers = new FollowingFollower().setFollowingUserId(id)
                    .setId(id)
                    .setFollowers(instaUsers);


            String end_cursor = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_followed_by")).get("page_info")).get("end_cursor");

            boolean has_next_page = (boolean) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_followed_by")).get("page_info")).get("has_next_page");

            firstfollowingFollowers.setEndCursor(end_cursor);

            followingFollowersRepository.save(firstfollowingFollowers);

            while (has_next_page) {
                String next = next(id, number, end_cursor);
                jsonObject = new JSONObject(next);
                postedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                        .get("data"))
                        .get("user")).get("edge_followed_by"))
                        .get("edges");
                end_cursor = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                        .get("data"))
                        .get("user")).get("edge_followed_by")).get("page_info")).get("end_cursor");

                has_next_page = (boolean) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                        .get("data"))
                        .get("user")).get("edge_followed_by")).get("page_info")).get("has_next_page");


                FollowingFollower followingFollower = followingFollowersRepository.findById(id)
                        .orElseThrow(Exception::new);
                followingFollower.setEndCursor(end_cursor);

                for (int i = 0; i < postedges.length(); i++) {
                    JSONObject node = (JSONObject) postedges.getJSONObject(i).get("node");
                    boolean is_private = (boolean) node.get("is_private");
                    String followingid = (String) node.get("id");
                    String username = (String) node.get("username");
                    String full_name = (String) node.get("full_name");
                    InstaUser instaUser = new InstaUser(followingid, username, full_name, is_private);
                    followingFollower.getFollowers().add(instaUser);
                }
                followingFollowersRepository.save(followingFollower);
            }

            client.close();
        } else {
            System.err.println("fucked req");

        }

    }


    @GetMapping("/follow/{id}/{aftertoken}")
    public void other(@PathVariable String id, @PathVariable String aftertoken) throws Exception {
        boolean has_next_page = true;
        while (has_next_page) {
            String next = next(id, 150, aftertoken);
            JSONObject jsonObject = new JSONObject(next);
            JSONArray postedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_followed_by"))
                    .get("edges");
            aftertoken = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_followed_by")).get("page_info")).get("end_cursor");

            has_next_page = (boolean) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("user")).get("edge_followed_by")).get("page_info")).get("has_next_page");

            FollowingFollower followingFollower = followingFollowersRepository.findById(id)
                    .orElseThrow(Exception::new);
            followingFollower.setEndCursor(aftertoken);
            for (int i = 0; i < postedges.length(); i++) {
                JSONObject node = (JSONObject) postedges.getJSONObject(i).get("node");
                boolean is_private = (boolean) node.get("is_private");
                String followingid = (String) node.get("id");
                String username = (String) node.get("username");
                String full_name = (String) node.get("full_name");
                InstaUser instaUser = new InstaUser(followingid, username, full_name, is_private);
                followingFollower.getFollowers().add(instaUser);
            }
            followingFollowersRepository.save(followingFollower);
        }


    }


    public static String next(String id, int number, String hashafter) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();
        //TODO NEED TO BE AUTHENTICATED WITH COOCKIES
        String par = String.format("{\"id\":\"%s\",\"include_reel\":true,\"fetch_mutual\":false,\"first\":%d,\"after\":\"%s\"}", id, number, hashafter);
        String ev = URLEncoder.encode(par);//first 1 i see
        String url = "https://www.instagram.com/graphql/query/?query_hash=c76146de99bb02f6415203be841dd25a&variables=" + ev;

        HttpGet httpPost = new HttpGet(url);
        httpPost.setHeader("x-csrftoken", "k1brA3VGgl4NxvMa2hVggm0KHMmsQNoA");
        httpPost.setHeader("x-ig-app-id", "936619743392459");
        httpPost.setHeader("x-ig-www-claim", "hmac.AR050a6T1x8GV3ajRljbbHZ8PdDvHeGf92e5aat3GEOxYby_\n");
        httpPost.setHeader("x-instagram-ajax", "4c064cca12e4");
        httpPost.setHeader("x-requested-with", "XMLHttpRequest");
        httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("cookie", "ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; csrftoken=k1brA3VGgl4NxvMa2hVggm0KHMmsQNoA; shbid=4534; shbts=1579880039.7452745; ds_user_id=11378716565; sessionid=11378716565%3AqsWar2zkpTvpWH%3A13; rur=VLL; urlgen=\"{\\\"212.80.12.73\\\": 44889}:1iv863:5SXMcCjQX682KjP8IXvxXuU9kKs\"");

        CloseableHttpResponse response = client.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        long contentLength = response.getEntity().getContentLength();//it should not be 0

        if (statusCode == 200) {

            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 6522728);
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }

            client.close();
            return sb.toString();
        } else {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 6522728);
            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            throw new RuntimeException("fucked");
        }
    }


}
