package com.god.economics.crawllers.instagram.api.hashtags;

import com.god.economics.crawllers.Reqs;
import com.god.economics.crawllers.instagram.all.IdProccess;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;

/**
 * created By gOD on 6/26/2020 7:12 PM
 */

public class HashtagApi {

    public static void main(String[] args) {
        new HashtagApi("آرایشگاه").startGetUserIds(possibleUserIds -> {
            System.out.println(possibleUserIds.size());
            FileWriter writer;
            try {
                writer = new FileWriter("ids_new" + ".txt", true);
                for (String possibleUserId : possibleUserIds) {
                    writer.write(possibleUserId + "\n");
                }
                writer.flush();
                writer.close();
                System.out.println(possibleUserIds.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }


    private String tagname;

    public HashtagApi(String tagname) {
        this.tagname = tagname;
    }

    private void startGetUserIds(Consume consume) {
        try {
            String first = first(tagname);


            JSONObject jsonObject = new JSONObject(first);

            JSONArray postedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) (jsonObject).get("entry_data")).get("TagPage")).getJSONObject(0)
                    .get("graphql")).get("hashtag")).get("edge_hashtag_to_media"))
                    .get("edges");

            HashSet<String> possibleUserIds = new HashSet<>();
            proccessEdges(postedges, possibleUserIds);
            consume.consume(possibleUserIds);


            JSONArray toppostedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("entry_data")).get("TagPage")).getJSONObject(0)
                    .get("graphql")).get("hashtag")).get("edge_hashtag_to_top_posts"))
                    .get("edges");

            possibleUserIds = new HashSet<>();
            proccessEdges(toppostedges, possibleUserIds);
            consume.consume(possibleUserIds);

            JSONObject page_info = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("entry_data")).get("TagPage")).getJSONObject(0)
                    .get("graphql")).get("hashtag")).get("edge_hashtag_to_media"))
                    .get("page_info");

            boolean has_next_page = (boolean) page_info.get("has_next_page");
            String end_cursor = (String) page_info.get("end_cursor");
            /*
             * {"tag_name":"mezon","first":1,"after":"QVFEc0FiZkNncGJVU0dGQ2czOENzWGlfbFBzZEpyUVZJc1BXN1kxeGpjMm15SFlIUXV4bHNzOWJ6cGt1N204QVNlaVVUQWV2QlYteU1rNzFqUlphS3Y3LQ=="}
             *  {"tag_name":"mezon","first":10,"after":"QVFBVUlvS1MwOTVaaXYwR01JVURGLU0yYWlYbjFLSUExVnlFYm5rT0lSSDZkNUxHczJMbU5xQWJwMkFkOHU5dWdObDVILVNKQ3dtSWE1YThXRkpsZnMtdQ=="}
             * */
            System.out.println(end_cursor);

            while (has_next_page) {
                String mezon = null;
                try {
                    mezon = next(tagname, end_cursor);
                } catch (IOException e) {
                    try {
                        mezon = next(tagname, end_cursor);
                    } catch (IOException ex) {
                        System.err.println(ex);

                    }
                }
//            System.out.println(mezon);

                jsonObject = new JSONObject(mezon);

                postedges = (JSONArray) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                        .get("data"))
                        .get("hashtag")).get("edge_hashtag_to_media"))
                        .get("edges");

                page_info = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject
                        .get("data"))
                        .get("hashtag")).get("edge_hashtag_to_media"))
                        .get("page_info");

                has_next_page = (boolean) page_info.get("has_next_page");
                end_cursor = (String) page_info.get("end_cursor");


                possibleUserIds = new HashSet<>();
                proccessEdges(postedges, possibleUserIds);
                consume.consume(possibleUserIds);
                System.out.println(end_cursor);
                System.out.println(possibleUserIds.size());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static String next(String tag, String hashafter) throws IOException {
        // QVFCOFdYY1pnNWxYTm1MSGVIT1VaOXUyakRiTW5KTGtKc2xBUmJ3T3pBRUkwZFpHMDFSc0lQSXI2cnFmaHlRcEJwYkU1UGZVblJqZ3YzSnJEOTkxUWdpRg==
        String par = String.format("{\"tag_name\":\"%s\", \"first\":10, \"after\":\"%s\"}", tag, hashafter);
        String ev = URLEncoder.encode(par);//first 1 i see

        String url = "https://www.instagram.com/graphql/query/?query_hash=bd33792e9f52a56ae8fa0985521d141d&variables=" + ev;

        return Reqs.getReq(url);


    }

    static void proccessEdges(JSONArray edges, HashSet<String> possibleIds) {

        for (int i = 0; i < edges.length(); i++) {
            JSONObject jsonObject = edges.getJSONObject(i);
            String id = (String) ((JSONObject) ((JSONObject) jsonObject
                    .get("node"))
                    .get("owner"))
                    .get("id");

            possibleIds.add(id);
        }

    }

    public static String first(String tag) throws IOException {
        String s = "https://www.instagram.com/explore/tags/" + tag + "/";

        Document document = Jsoup.connect(s).get();

        String script = document.getElementsByTag("script").get(3).html().split("window._sharedData = ")[1].split(";")[0];
        JSONObject jsonObject = new JSONObject(script);

        //_sharedData.entry_data.TagPage[0].graphql.hashtag.edge_hashtag_to_media.page_info.has_next_page
        JSONObject page_info = (JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("entry_data")).get("TagPage")).getJSONObject(0)
                .get("graphql")).get("hashtag")).get("edge_hashtag_to_media"))
                .get("page_info");

//        System.out.println(script);
        return script;
    }


}
