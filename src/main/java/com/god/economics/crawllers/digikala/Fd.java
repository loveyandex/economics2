package com.god.economics.crawllers.digikala;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * created By gOD on 11/5/2019 3:08 AM
 */

public class Fd {
    public static void main(String[] args) throws IOException {
        String url = "sd";
        url = "https://www.digikala.com/product/dkp-383379";
        Document document = Jsoup.connect(url).get();
        Element body = document.body();

        Elements type = document.getElementsByAttributeValue("type", "application/ld+json");
        String value = ((DataNode) type.get(0).childNodes().get(0)).getWholeData();
        JSONObject jsonObject = new JSONObject(value);


        System.out.println(value);


    }
}
