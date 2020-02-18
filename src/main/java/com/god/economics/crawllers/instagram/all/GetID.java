package com.god.economics.crawllers.instagram.all;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * created By gOD on 1/25/2020 3:32 AM
 */

public class GetID {

    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
//        Document document = Jsoup.connect("https://www.instagram.com/p/B5_VVcXBuBr/").get();B51_ZlfhnQj
        Document document = Jsoup.connect("https://www.instagram.com/p/B7rOGLcBvFH/").get();

        Element property = document.getElementsByAttributeValue("property", "al:ios:url").get(0);
        String content = property.attr("content");
        String id = content.split("id=")[1];

        System.out.println(id);
        System.out.println((System.currentTimeMillis() - l));


    }
}
