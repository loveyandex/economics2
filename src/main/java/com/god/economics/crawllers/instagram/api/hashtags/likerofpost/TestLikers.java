package com.god.economics.crawllers.instagram.api.hashtags.likerofpost;

import com.god.economics.crawllers.instagram.LoginConfig;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import okhttp3.ResponseBody;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author AbolfazlcxmgqXzA3YS = {ContinuedEdges@2619}
 */
public class TestLikers {


    public static void main(String[] args) throws Exception {
        try (MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));) {
            MongoDatabase database = mongoClient.getDatabase("instagram");
            MongoCollection<Document> collection = database.getCollection("likers_post");
            String cxmgqXzA3YS1 = "CXMGQXzA3YS";
//            cxmgqXzA3YS1 = "CXl-mPdtowb";

            ContinuedEdges cxmgqXzA3YS = new TestLikers().findfirstlikmers(cxmgqXzA3YS1);
            System.out.println(cxmgqXzA3YS);
            collection.insertOne(Document.parse(cxmgqXzA3YS.data));
            System.out.println("inserted");

            ContinuedEdges end_cursor = new TestLikers().next(cxmgqXzA3YS1, cxmgqXzA3YS.end_cursor);
            System.out.println(cxmgqXzA3YS);
            collection.insertOne(Document.parse(end_cursor.data));
            System.out.println("inserted end_cursor");


        } catch (Exception e) {
            System.out.println();
        }


    }

    public ContinuedEdges findfirstlikmers(String shortcode) throws Exception {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();

        String par2 = String.format("{\"shortcode\":\"%s\",\"include_reel\":true,\"first\":24}", shortcode);
        String url = "https://www.instagram.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        LikersOfPostUsersApi likersOfPostUsersApi = retrofit.create(LikersOfPostUsersApi.class);
        objectObjectHashMap.put("query_hash", "d5d763b1e2acf209d62d22d184488e57");
        objectObjectHashMap.put("variables", par2);
        Call<ResponseBody> call = likersOfPostUsersApi.getLastFollowings(LoginConfig.cookie
                , LoginConfig.csrftoken, objectObjectHashMap);

        Response<ResponseBody> execute;
        try {
            execute = call.execute();
            ResponseBody body = execute.body();
            String data = Objects.requireNonNull(body).string();


            JSONObject jsonObject = new JSONObject(data);

            JSONObject edgelikedby = (JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("shortcode_media"))
                    .get("edge_liked_by");

            JSONArray postedges = (JSONArray) edgelikedby
                    .get("edges");

            JSONObject page_info = (JSONObject) edgelikedby.get("page_info");
            String end_cursor = page_info.get("end_cursor").toString();
            boolean has_next_page = ((boolean) page_info.get("has_next_page"));

            return new ContinuedEdges(postedges, end_cursor, has_next_page, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public ContinuedEdges next(String shortcode, String end) throws Exception {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();

        String par2 = String.format("{\"shortcode\":\"%s\" ,\"first\":24, \"after\":\"%s\"}", shortcode, end);
        String url = "https://www.instagram.com/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
        LikersOfPostUsersApi likersOfPostUsersApi = retrofit.create(LikersOfPostUsersApi.class);
        objectObjectHashMap.put("query_hash", "d5d763b1e2acf209d62d22d184488e57");
        objectObjectHashMap.put("variables", par2);
        Call<ResponseBody> call = likersOfPostUsersApi.getLastFollowings(LoginConfig.cookie
                , LoginConfig.csrftoken, objectObjectHashMap);

        Response<ResponseBody> execute;
        try {
            execute = call.execute();
            ResponseBody body = execute.body();
            String data = Objects.requireNonNull(body).string();


            JSONObject jsonObject = new JSONObject(data);

            JSONObject edgelikedby = (JSONObject) ((JSONObject) ((JSONObject) jsonObject
                    .get("data"))
                    .get("shortcode_media"))
                    .get("edge_liked_by");

            JSONArray postedges = (JSONArray) edgelikedby
                    .get("edges");

            JSONObject page_info = (JSONObject) edgelikedby.get("page_info");
            String end_cursor = page_info.get("end_cursor").toString();
            boolean has_next_page = ((boolean) page_info.get("has_next_page"));

            return new ContinuedEdges(postedges, end_cursor, has_next_page, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }


}
