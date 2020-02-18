package com.god.economics.crawllers.instagram.donyacomment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TopdownComments {


    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.instagram.com/p/B8grxXdpsod/").get();
        Elements type = document.getElementsByAttributeValue("type", "application/ld+json");

        String json = type.get(0).html().replaceAll("<script type=\"application/ld+json\">", "")
                .replaceAll("<script ", "")
                .replaceAll("<script", "")
                .replaceAll("type=\"application/ld+json\"", "")
                .replaceAll(">", "")
                .replaceAll(" </script>", "");

        System.out.println();


    }

}
