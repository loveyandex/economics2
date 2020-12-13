package com.god.economics.crawllers.digikala;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * created By gOD on 10/6/2020 9:12 PM
 */

public class incredibleOffers {

    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://www.digikala.com/incredible-offers/")
                    .get();
            Elements script1 = document.head().getElementsByTag("script");
            Element script = script1.get(9);

            JSONArray offrs = (JSONArray) ((JSONObject) ((JSONObject) new JSONArray(document.head().getElementsByTag("script").get(3).html()
                    .split(" var dataLayerData =")[1].split("]}}];")[0] + "]}}]").get(0))
                    .get("ecommerce")).get("impressions");

            for (int i = 0; i < offrs.length(); i++) {
                JSONObject offer = (JSONObject) offrs.get(i);
                Object id = offer.get("id");
                Object name = offer.get("name");
                System.out.println(id+name.toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
