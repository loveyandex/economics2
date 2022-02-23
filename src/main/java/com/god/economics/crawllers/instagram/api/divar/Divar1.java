package com.god.economics.crawllers.instagram.api.divar;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @author Abolfazl
 */
public class Divar1 {
    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect("https://divar.ir/s/tehran/electronic-devices?goods-business-type=marketplace").get();
        Element body = document.body();


        System.out.println(body);
    }

}
