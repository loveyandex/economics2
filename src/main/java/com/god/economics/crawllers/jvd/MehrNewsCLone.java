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

public class MehrNewsCLone {
    public static void main(String[] args) throws IOException {
        int pgnumber = 0;
        int maxpgnm = 4;
        LinkedList<Response> responses = new LinkedList<>();
        String host = "https://www.mehrnews.com";

        Element body = Jsoup.connect(host).get().body();


        Element box1 = body.getElementById("box1");

        Element a = box1.getElementsByTag("a").get(2);
        String titile = a.text();
        String href1 = host + a.attr("href");
        responses.add(new Response(titile, href1, null));


        Element box2 = body.getElementById("box2");

        box2.getElementsByTag("li").forEach(element -> {
            Element a1 = element.getElementsByTag("a").get(1);
            Element a2 = element.getElementsByTag("a").get(2);

            String tit = a1.text() + "\n" + a2.text();
            String href = host + a1.attr("href");

            responses.add(new Response(tit, href, null));

        });


        Element box520 = body.getElementById("box520");
        box520.getElementsByTag("li").forEach(element -> {
            Element a1 = element.getElementsByTag("a").get(0);
            String tit = a1.text() ;
            String href = host + a1.attr("href");
            responses.add(new Response(tit, href, null));

        });


        Element box3 = body.getElementById("box3");
        box3.getElementsByTag("li").forEach(element -> {
            Element a1 = element.getElementsByTag("a").get(0);
            String tit = a1.text() ;
            String href = host + a1.attr("href");
            responses.add(new Response(tit, href, null));

        });




        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(responses, new TypeToken<List<Response>>() {
        }.getType());


        System.out.println(element.toString());


    }
}
