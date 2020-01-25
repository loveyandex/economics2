package com.god.economics.crawllers.instagram.comment;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * created By gOD on 1/25/2020 9:11 PM
 */

public class HaghTag {

    public static void main(String[] args) throws IOException {
        String s = "https://www.instagram.com/explore/tags/mezon/";

        Document document = Jsoup.connect(s).get();

        String script = document.getElementsByTag("script").get(3).html().split("window._sharedData = ")[1].split(";")[0];
        JSONObject jsonObject = new JSONObject(script);
        System.out.println(script);




    }

}
