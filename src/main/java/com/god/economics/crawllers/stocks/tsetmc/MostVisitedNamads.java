package com.god.economics.crawllers.stocks.tsetmc;

import com.god.economics.crawllers.bama.models.CarRepository;
import com.god.economics.crawllers.stocks.models.Namad;
import com.god.economics.crawllers.stocks.models.NamadRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * created By gOD on 12/17/2019 9:05 PM
 */

@RestController
public class MostVisitedNamads {


    @Autowired
    private NamadRepository namadRepository;

    @GetMapping("/addnamads")
    public void main() throws IOException {
        String host = "http://tsetmc.com/";
        Document document = Jsoup.connect(host + "Loader.aspx?Partree=151317&Type=MostVisited&Flow=1").get();

        Element body = document.body();

        Element tbody = body.getElementsByTag("tbody").get(0);

        Elements namads = tbody.getElementsByTag("tr");

        for (Element namad : namads) {
            Elements parameters = namad.getElementsByTag("td");

            String username = parameters.get(0).text();
            String link = host + parameters.get(0).getElementsByTag("a").get(0).attr("href");

            String token = link.split("i=")[1];

            String name = parameters.get(1).text();

            Namad namadobj = new Namad()
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
