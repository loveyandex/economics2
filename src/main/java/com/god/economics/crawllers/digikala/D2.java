package com.god.economics.crawllers.digikala;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class D2 {
    static ArrayList<Cat> allcats = new ArrayList<>();
    private static int id = 0;

    public static void main(String[] args) throws IOException {

        String url = "https://www.digikala.com/";
        Element body = Jsoup.connect(url).get().body();

        Element element = (Element) body.getElementsByClass("c-navi-new-list__category-item").get(0).childNodes().get(1);
        Element element1 = element.getElementsByClass("c-navi-new-list__inner-categories").get(0);

        ArrayList<Cat> subs = new ArrayList<>();
        Cat cat0 = new Cat()
//                .setSubcats(subs)
                .setParentId(-1).setName("root").setId(id++);
        allcats.add(cat0);

        for (Node childNode : element1.childNodes()) {
            String href = childNode.attr("href");
            String text1 = ((Element) childNode).text();
            System.out.println(text1);

            Element body1 = Jsoup.connect(url + href).get().body();
            Element plainlist = body1.getElementsByClass("c-catalog__plain-list").get(0);
            Cat cat = new Cat()
                    .setParentId(cat0.id)
                    .setId(id++).setName(text1);

            allcats.add(cat);
            Node ul = body1.getElementsByAttributeValue("class", "c-filter c-filter--catalog").get(0).childNodes().get(0);
            ulLiA(ul, cat);
        }

        System.out.println(new Gson().toJson(cat0));
        System.out.println(new Gson().toJson(allcats));
    }


    public static void ulLiA(Node ul, Cat cat) {

        for (Node li : ul.childNodes()) {
            List<Node> childNodes = li.childNodes();
            String text = ((Element) childNodes.get(0)).text();
            Cat cat1 = new Cat().setId(id++).setParentId(cat.id)
//                        .setSubcats(sub1)
                    .setName(text);
            allcats.add(cat1);
            if (childNodes.size()>1)
            {
                Node n2 = childNodes.get(1);
                ulLiA(n2, cat1);
            }



        }

    }

}
