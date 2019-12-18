package com.god.economics.crawllers.digikala;

import com.god.economics.crawllers.digikala.models.Item;
import com.google.gson.Gson;
import org.json.JSONArray;
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
        url = "https://www.digikala.com/search/category-accessoeys/";
        url = "https://www.digikala.com/search/category-cell-phone-pouch-cover/";
        url = "https://www.digikala.com/search/category-cell-phone-pouch-cover/?pageno=2&sortby=4";
        Document document = Jsoup.connect(url).get();
        Element body = document.body();


        Elements scripts = document.head().getElementsByTag("script");


        Element sc = scripts.get(scripts.size() - 3);
        String s=((String) ((DataNode) scripts.get(scripts.size() - 5).childNodes().get(0)).getWholeData());
        int click_impression = s.indexOf("click_impression");
        int i = s.indexOf(";\n" + "                        var page_search_url");

        String substring = s.substring(click_impression, i).substring(19);
        JSONArray objects = new JSONArray(substring);

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
