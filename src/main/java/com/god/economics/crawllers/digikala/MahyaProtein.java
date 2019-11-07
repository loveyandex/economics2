package com.god.economics.crawllers.digikala;

import com.god.economics.GoodClassificationRepo;
import com.god.economics.crawllers.digikala.models.GoodCLassification;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * created By gOD on 9/18/2019 1:30 AM
 */


@RestController
public class MahyaProtein {

    @Autowired
    private GoodClassificationRepo goodClassificationRepo;

    @GetMapping("/root")
    public void  ss() throws IOException {
//        Document document = Jsoup.connect("https://www.digikala.com/brand/mahya-protein").get();
        String url = "https://www.digikala.com/";
        Element body = Jsoup.connect(url).get().body();
        Element elementByClass = body.getElementsByClass("c-navi-new-list__inner-categories").get(0);



        for (Node childNode : elementByClass.childNodes()) {

            Element king = ((Element) childNode);
            String name = king.text();
            String href = king.attr("href");

            GoodCLassification goodCLassification = new GoodCLassification(name, href);
            goodClassificationRepo.save(goodCLassification);

        }








    }
}
