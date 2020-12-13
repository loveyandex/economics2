package com.god.economics.crawllers.digikala;

import com.god.economics.crawllers.digikala.models.Item;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * created By gOD on 10/7/2020 1:06 AM
 */

public class IdInfo {


    public static void main(String[] args) {
        String id = "";

        String url = "sd";
        url = "https://www.digikala.com/product/dkp-383379";
//        url = "https://www.digikala.com/search/category-accessoeys/";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element body = document.body();


        Elements elementsByAttribute = body.getElementsByAttribute("data-enhanced-ecommerce");
        for (Element element : elementsByAttribute) {
            String attr = element.attr("data-enhanced-ecommerce");
            Item item = new Gson().fromJson(attr, Item.class);
            System.out.println(attr);

        }


        Elements type = document.getElementsByAttributeValue("type", "application/ld+json");
        String value = ((DataNode) type.get(0).childNodes().get(0)).getWholeData();
        JSONObject jsonObject = new JSONObject(value);


        System.out.println(value);

    }
}
