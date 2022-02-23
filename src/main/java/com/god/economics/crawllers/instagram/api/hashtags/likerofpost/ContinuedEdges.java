 package com.god.economics.crawllers.instagram.api.hashtags.likerofpost;
import org.json.JSONArray;

public class ContinuedEdges {
    public  String data;
     JSONArray postedges;
    public String end_cursor;
    public boolean has_next_page;

    public ContinuedEdges(JSONArray postedges, String end_cursor, boolean has_next_page,String data) {
        this.postedges = postedges;
        this.end_cursor = end_cursor;
        this.has_next_page = has_next_page;
        this.data = data;
    }
}
