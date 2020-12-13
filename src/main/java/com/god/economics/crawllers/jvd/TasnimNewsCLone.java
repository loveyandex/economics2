package com.god.economics.crawllers.jvd;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * created By gOD on 12/6/2020 4:06 PM
 */

public class TasnimNewsCLone {
    public static void main(String[] args) throws IOException {
        int pgnumber = 0;
        int maxpgnm = 4;
        LinkedList<Response> responses = new LinkedList<>();
        String host = "https://www.tasnimnews.com";
        while (pgnumber++ < maxpgnm) {
            String url = "https://www.tasnimnews.com/fa/top-stories?page=" + pgnumber;

            Element body = Jsoup.connect(url).get().body();


            Elements headlines = body.getElementsByAttributeValue("class", "news-container");

            headlines.get(0).getElementsByTag("article").forEach(element -> {
                Element a = element.getElementsByTag("a").get(0);
                String h2 = a.getElementsByTag("h2").get(0).text();
                String href1 = host + a.attr("href");
                responses.add(new Response(h2, href1, null));

            });


        }
        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(responses, new TypeToken<List<Response>>() {
        }.getType());


        System.out.println(element.toString());


    }
}
