package com.god.economics.crawllers.digikala;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * created By gOD on 9/18/2019 1:30 AM
 */

public class MahyaProtein {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.digikala.com/brand/mahya-protein").get();
    }
}
