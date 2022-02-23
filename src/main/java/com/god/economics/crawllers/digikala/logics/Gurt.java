package com.god.economics.crawllers.digikala.logics;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * @author Abolfazl
 */
public class Gurt {


    public static void main(String[] args) throws IOException {


        int page=1;
        Connection cnc = Jsoup.connect("https://enamad.ir/DomainListForMIMT/Index/"+page).header("Referer","https://enamad.ir/");
        Document document = cnc.get();

        Element body = document.body();

        Element div_content = body.getElementById("Div_Content");

        Elements rows = div_content.getElementsByClass("row");


        for (Node row : rows) {

            List<Node> nodeList = row.childNodes();
            String r = ((Element) nodeList.get(1)).text();
            Elements a = ((Element) nodeList.get(3)).getElementsByTag("a");
            Element ew = a.get(0);
            String ewhref = ew.attr("href");
            Element w = a.get(1);
            String name = ((Element) nodeList.get(5)).text();

            String state = ((Element) nodeList.get(7)).text();

            String city = ((Element) nodeList.get(9)).text();
            String exprition = ((Element) nodeList.get(13)).text();


            Connection c = Jsoup.connect(ewhref).header("Referer","https://enamad.ir/");
            Element body1 = c.get().body();

            String latlons = body1.getElementsByTag("script").get(2).html().split("setView\\(\\[")[1].split("]")[0];

            String[] split = latlons.split(",");
            Elements mainuls = body1.getElementsByClass("mainul");

            Element main0 = mainuls.get(0);
            Element main1 = mainuls.get(1);




            System.out.println();

        }



        System.out.println();


    }
}
