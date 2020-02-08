package com.god.economics.crawllers.instagram.farzadproblems;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class CitiesOfIran {

    public static void main(String[] args) throws IOException {
        Element body = Jsoup.connect("https://fa.wikipedia.org/wiki/%D9%81%D9%87%D8%B1%D8%B3%D8%AA_%D8%B4%D9%87%D8%B1%D9%87%D8%A7%DB%8C_%D8%A7%DB%8C%D8%B1%D8%A7%D9%86_%D8%A8%D8%B1_%D9%BE%D8%A7%DB%8C%D9%87_%D8%AC%D9%85%D8%B9%DB%8C%D8%AA").get().body();
        Element tbody = body.getElementsByTag("tbody").get(1);
        FileWriter writer = new FileWriter("provinceAndtowns", false);

        Elements tr = tbody.getElementsByTag("tr");
        for (int i = 1; i < tr.size(); i++) {
            Element element = tr.get(i);
            String province = tr.get(i).getElementsByTag("td").get(1).text();
            String town = tr.get(i).getElementsByTag("td").get(0).text();
            String x = town + ">>>" + province;
            if (x.contains("ى")) {
                System.out.println(x);
            }
            writer.write(x.replaceAll("\n", "") + "\r\n");
            writer.flush();
            System.out.println(x);
        }
        writer.close();

    }
}
