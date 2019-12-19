package com.god.economics.crawllers.stocks.tsetmc;

import com.god.economics.crawllers.stocks.models.AllNamad;
import com.god.economics.crawllers.stocks.models.AllNamadRepository;
import com.god.economics.crawllers.stocks.models.Namad;
import com.god.economics.crawllers.stocks.models.NamadRepository;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * created By gOD on 12/17/2019 9:05 PM
 */

@RestController
public class AllNamads {


    @Autowired
    private AllNamadRepository namadRepository;

    @GetMapping("/allnamads")//todo fucked his request
    public void main() throws IOException {
        String host = "http://tsetmc.com/";
        Document document = Jsoup.connect(host + "Loader.aspx?ParTree=15131F").get();

        Element body = document.body();

        Element tbody = body.getElementById("main");


        Elements namads = tbody.getElementsByTag("a");

        for (int i = 0; i < namads.size(); i += 2) {
            Element namad = namads.get(i);
            Element namadForName = namads.get(i + 1);

            String username = namad.text();
            String link = host + namad.attr("href");

            String token = link.split("i=")[1];

            String name = namadForName.text();

            AllNamad namadobj = new AllNamad()
                    .setLink(link)
                    .setNamad(username)
                    .setToken(token)
                    .setName(name);

            namadRepository.save(namadobj);
//            String yesterdayPrice = parameters.get(0).text();
//            String yesterdayPrice = parameters.get(0).text();


        }


    }

    @GetMapping("/allnamads2")
    public void maisdn() throws IOException {
        String host = "http://tsetmc.com/";


        OkHttpClient client = new OkHttpClient();
        String url = "http://www.tsetmc.com/tsev2/data/MarketWatchInit.aspx?h=0&r=0";


        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split(";")[3].split(",");

            System.out.println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<Element> namads = null;

        for (int i = 0; i < namads.size(); i += 2) {
            Element namad = namads.get(i);
            Element namadForName = namads.get(i + 1);

            String username = namad.text();
            String link = host + namad.attr("href");

            String token = link.split("i=")[1];

            String name = namadForName.text();

            AllNamad namadobj = new AllNamad()
                    .setLink(link)
                    .setNamad(username)
                    .setToken(token)
                    .setName(name);

            namadRepository.save(namadobj);
//            String yesterdayPrice = parameters.get(0).text();
//            String yesterdayPrice = parameters.get(0).text();


        }


    }
}
