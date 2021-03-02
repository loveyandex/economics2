package com.god.economics.crawllers.instagram.api.hashtags.posts;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.timeline.TimelineAction;
import com.god.economics.crawllers.Reqs;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.god.economics.crawllers.instagram.api.story.run.igClient;

/**
 * created By gOD on 12/15/2020 5:06 AM
 */

public class Flow {

    private ArrayList<String> srcs = new ArrayList<>();
    private String src;
    private JSONObject jsonObject;
    private String shortcode;
    private IGClient igClient;

    public static void main(String[] args) {
        new Flow()
                .run("CJA5IqZg7PO");

    }


    public void run(String cja5IqZg7PO) {
        this.shortcode = cja5IqZg7PO;

        String queryparam = String.format("{\"shortcode\":\"%s\",\"child_comment_count\":3,\"fetch_comment_count\":40,\"parent_comment_count\":24,\"has_threaded_comments\":true}",
                shortcode);

        String url = "https://www.instagram.com/graphql/query/?query_hash=a9441f24ac73000fa17fe6e6da11d59d&variables=" + queryparam;

        try {
            String req = Reqs.getReq(url);
            this.jsonObject = (JSONObject) ((JSONObject) new JSONObject(req).get("data")).get("shortcode_media");
            String typename = jsonObject.get("__typename").toString();

            GraphType(typename);

            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void GraphType(String typename) {
        switch (typename) {
            case "GraphVideo":
                GraphVideo(jsonObject);
                break;
            case "GraphImage":
                GraphImage(jsonObject);
                break;
            case "GraphSidecar":
                Graphsidecars(jsonObject);
                break;
        }
    }

    private void Graphsidecars(JSONObject jsonObject) {

        JSONArray objects = (JSONArray) ((JSONObject) jsonObject.get("edge_sidecar_to_children")).get("edges");
        objects.forEach(o -> {
            JSONObject node = (JSONObject) ((JSONObject) o).get("node");
            String typename = node.get("__typename").toString();
            JSONArray display_resources = (JSONArray) node.get("display_resources");
            String src = ((JSONObject) display_resources.get(2)).get("src").toString();
            this.srcs.add(src);
        });
        try {
            uploadsidecars();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void GraphImage(JSONObject jsonObject) {
        JSONArray display_resources = (JSONArray) jsonObject.get("display_resources");
        String src = ((JSONObject) display_resources.get(2)).get("src").toString();
        try {
            uploadOneimgWithIG(src);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void GraphVideo(JSONObject jsonObject) {


    }


    private void uploadOneimgWithIG(String src) throws Exception {
        BufferedImage bufferedImageu = ImageIO.read(new URL(src));
        File output = new File("output.jpg");
        ImageIO.write(bufferedImageu, "jpg", output);
        System.err.println("uploading ");
        IGClient igClient = igClient();
        igClient.actions().timeline().uploadPhoto(output, "").thenApply(mediaConfigureTimelineResponse -> {
            return "";
        }).exceptionally(throwable -> {
            return "";
        }).join();

    }


    private void uploadsidecars() throws Exception {

        List<TimelineAction.SidecarInfo> infos = new ArrayList<>();
        srcs.forEach(s -> {
            BufferedImage bufferedImageu = null;
            try {
                bufferedImageu = ImageIO.read(new URL(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
            File output = new File("output.jpg");
            try {
                ImageIO.write(bufferedImageu, "jpg", output);
                infos.add(TimelineAction.SidecarPhoto.from(output));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        System.err.println("uploading sidecars");
        this.igClient = igClient();

        igClient.actions().timeline().uploadAlbum(infos, "هرسوالی دایرکت در خدمت هستیم")
                .thenApply(mediaConfigureTimelineResponse -> {
            return "";
        }).exceptionally(throwable -> {
            return "";
        }).join();

    }


}
