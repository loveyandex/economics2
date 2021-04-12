package com.god.economics.crawllers.digikala;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class D {
    public static void main(String[] args) throws IOException {

        String url = "https://www.digikala.com/";
        Element body = Jsoup.connect(url).get().body();

        Element element = (Element) body.getElementsByClass("c-navi-new-list__category-item").get(0).childNodes().get(1);
        Element element1 = element.getElementsByClass("c-navi-new-list__inner-categories").get(0);

        ArrayList<Cat> subs = new ArrayList<>();
        ArrayList<Cat> allcats = new ArrayList<>();
        int id = 1;
        Cat cat0 = new Cat()
//                .setSubcats(subs)
                .setParentId(-1).setName("root").setId(0);
        allcats.add(cat0);

        for (Node childNode : element1.childNodes()) {
            String href = childNode.attr("href");
            String text1 = ((Element) childNode).text();
            System.out.println(text1);

            Element body1 = Jsoup.connect(url + href).get().body();
            Element plainlist = body1.getElementsByClass("c-catalog__plain-list").get(0);

            Elements li = plainlist.getElementsByTag("li");
            ArrayList<Cat> sub = new ArrayList<>();
            Cat cat = new Cat()
//                    .setSubcats(sub)
                    .setParentId(cat0.id).setId(id++).setName(text1);
            allcats.add(cat);
            for (Node node : plainlist.childNodes()) {
                Elements li1 = ((Element) node).getElementsByTag("li");


                String text = ((Element) li1.get(0).childNodes().get(0)).getElementsByTag("a").get(0).text();
                System.out.println(text);

                ArrayList<Cat> sub1 = new ArrayList<>();
                Cat cat1 = new Cat().setId(id++).setParentId(cat.id)
//                        .setSubcats(sub1)
                        .setName(text);
                allcats.add(cat1);
                for (int i = 1; i < li1.size(); i++) {
                    Element element2 = li1.get(i);
                    String name = element2.text();
                    Cat catc = new Cat()
//                            .setSubcats(null)
                            .setParentId(cat1.id)
                            .setId(id++)
                            .setName(name);
                    allcats.add(catc);
//                    sub1.add(catc);
                    System.err.println(name);

                }

//                sub.add(cat1);
                System.out.println("-------------------------------------------");
            }

//            subs.add(cat);
            System.out.println(href);
        }


        System.out.println(new Gson().toJson(cat0));
        System.out.println(new Gson().toJson(allcats));
    }
}
