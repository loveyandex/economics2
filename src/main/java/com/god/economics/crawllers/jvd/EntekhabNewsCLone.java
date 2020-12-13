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

public class EntekhabNewsCLone {
    public static void main(String[] args) throws IOException {
        int pgnumber = 0;
        int maxpgnm = 4;
        LinkedList<Response> responses = new LinkedList<>();
        String host = "https://www.entekhab.ir";


        Element body = Jsoup.connect(host).get().body();


        Elements headlines = body.getElementsByAttributeValue("class", "im-news col-xs-36");


        headlines.get(0).getElementsByAttributeValue("class", "im-news-contents col-xs-36").get(0)
                .getElementsByAttributeValue("class", "im-news-content col-xs-36").forEach(element -> {
            Element a = element.getElementsByTag("a").get(0);
            String h2 = a.text();
            String href1 = host + a.attr("href");
            responses.add(new Response(h2, href1, null));

        });


        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(responses, new TypeToken<List<Response>>() {
        }.getType());


        System.out.println(element.toString());


    }
}
