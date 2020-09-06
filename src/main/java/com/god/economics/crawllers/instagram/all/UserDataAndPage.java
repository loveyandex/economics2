package com.god.economics.crawllers.instagram.all;

import com.god.economics.crawllers.instagram.clone.TimelineImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class UserDataAndPage {


    public static void main(String[] args) throws IOException, InterruptedException {


        OkHttpClient client = new OkHttpClient();
        String usename = "adidas.tandis";
        String url = "https://www.instagram.com/" + usename + "/?__a=1";


        Request request = new Request.Builder()
                .url(url)
                .header("cookie", "csrftoken=92rh9ugksst6s6P5wDDAAkjJdkIibV9j; ig_did=A30349A2-397A-49BC-BE9C-EA6994B5C2A8; mid=XzVkgAALAAGMsNEmH78Qob7DdcMS; ds_user_id=30299824247; sessionid=30299824247%3ATBS0U5dX8M8fgk%3A17; shbid=9033; shbts=1597334658.1844387; rur=ASH")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);

            if (jsonObject.isEmpty())
                System.out.println("empty user");

            Object graphql = jsonObject.get("graphql");

            JSONObject user = (JSONObject) (((JSONObject) graphql).get("user"));


            String bio = ((String) user
                    .get("biography"));

            String rxternalurl = (String) user.get("external_url");

            String full_name = (String) user.get("full_name");

            int edge_followed_by = ((int) ((JSONObject) user.get("edge_followed_by")).get("count"));
            int edge_follow = ((int) ((JSONObject) user.get("edge_follow")).get("count"));

            JSONObject edge_owner_to_timeline_media = (JSONObject) user.get("edge_owner_to_timeline_media");
            JSONObject page_info = (JSONObject) edge_owner_to_timeline_media.get("page_info");
            JSONArray edges = (JSONArray) edge_owner_to_timeline_media.get("edges");


            boolean has_next_page = (boolean) page_info.get("has_next_page");
            String end_cursor = (String) page_info.get("end_cursor");


            ArrayList<Object> posts = new ArrayList<>();
            int k = 0;
            while (k++ < 1) {
                for (int i = 0; i < edges.length(); i++) {
                    ArrayList<Object> objects = new ArrayList<>();
                    JSONObject edge = edges.getJSONObject(i);
                    JSONObject node = (JSONObject) edge
                            .get("node");
                    String typename = node.get("__typename").toString();

                    switch (typename) {
                        case "GraphVideo":
                            GraphVideo(node);
                            break;
                        case "GraphImage":
                            GraphImagestate(node, objects);
                            break;
                        case "GraphSidecar":
                            graphsidecarstate(node, objects);
                            break;
                    }
                    if (objects.size() > 0)
                        posts.add(objects);

                    // my mind is ignoring if by creating hash function for possible states
                    //this is done by neural network function approximating just like

                    System.out.println(typename);

                    String id = (String) ((JSONObject) node
                            .get("owner"))
                            .get("id");
                }
            }
            String element = new Gson().toJson(
                    posts,
                    new TypeToken<ArrayList<Object>>() {
                    }.getType());
            System.out.println(element);


            System.out.println(string);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void graphsidecarstate(JSONObject node, ArrayList<Object> objects) {
        JSONArray edges = (JSONArray) ((JSONObject) node.get("edge_sidecar_to_children")).get("edges");
        String caption = ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) node.get("edge_media_to_caption")).get("edges")).getJSONObject(0)).get("node"))).get("text").toString();
        if (caption.contains("خرید حضوری")) {
            String[] split = caption.split("خرید حضوری");
            caption = split[0].replaceAll("#adidastandis", "")
                    + "\n#adidasberan";
        }

        for (int i = 0; i < edges.length(); i++) {
            JSONObject node1 = (JSONObject) edges.getJSONObject(i).get("node");
            if (node1.get("__typename").toString().equals("GraphImage")) {
                String display_url = node1.get("display_url").toString();
                TimelineImage timelineImage = new TimelineImage(display_url, caption);
                objects.add(timelineImage);

            }


        }
        String element = new Gson().toJson(
                objects,
                new TypeToken<ArrayList<Object>>() {
                }.getType());
        System.out.println(element);


    }

    private static void GraphImagestate(JSONObject node, ArrayList<Object> objects) {

    }

    private static void GraphVideo(JSONObject node) {

    }

}
