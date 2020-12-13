package com.god.economics.crawllers.jvd;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created By gOD on 12/6/2020 4:06 PM
 */

public class CLone {
    public static void main(String[] args) throws IOException {
        String url = "https://www.tasnimnews.com";
        Element body = Jsoup.connect(url).get().body();

        LinkedList<Response> responses = new LinkedList<>();

        Element first = body.getElementsByClass("first").get(0);
        String ttile = first.text();
        String href = url + first.getElementsByTag("a").get(0).attr("href");
        System.out.println(ttile);
        System.out.println(href);

        responses.add(new Response(ttile, href, null));

        Elements headlines = body.getElementsByAttributeValue("class", "news-container headlines");

        headlines.get(0).getElementsByTag("article").forEach(element -> {
            Element a = element.getElementsByTag("a").get(0);
            String h2 = a.getElementsByTag("h2").get(0).text();
            String href1 = url + a.attr("href");
            responses.add(new Response(h2, href1, null));

        });

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(responses, new TypeToken<List<Response>>() {
        }.getType());


        System.out.println(element.toString());


    }
}
