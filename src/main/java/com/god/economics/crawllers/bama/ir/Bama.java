package com.god.economics.crawllers.bama.ir;

import com.god.economics.crawllers.bama.models.Brand;
import com.god.economics.crawllers.bama.models.BrandRepo;
import com.god.economics.crawllers.bama.models.Car;
import com.god.economics.crawllers.bama.models.CarRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * created By gOD on 12/11/2019 3:20 AM
 */

@RestController
public class Bama {


    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/uopdatebama")
    public List<Car> error() throws IOException {
        Connection connect = Jsoup.connect("https://bama.ir/price");
        Element body = connect.get().body();

        Elements sections = body.getElementsByClass("price-list-brand-section");


        long timeMillis = System.currentTimeMillis();

        for (Element section : sections) {
            Element allname = section.getElementsByClass("price-list-brand-name").get(0);

            Brand brand = new Brand()
                    .setName(allname.text());

            brandRepo.save(brand);


            System.out.println(allname.text().split("قیمت خودرو")[1]);

            Elements lis = section.getElementsByTag("li");
            for (Element li : lis) {
                Element modelName = li.getElementsByClass("sefr-model").get(0);


                Element modelLink = li.getElementsByClass("sefr-model-link").get(0);
                String href = modelLink.attr("href");
                System.out.println(href);

//                Elements scripts = Jsoup.connect(href).get().getElementsByTag("script");
//
//                String html = scripts.get(17).html();
//                System.out.println(html);
//                String var_jsColorString = html.substring(20, html.indexOf("var jsColorString") - 8);
//                JSONArray objects = new JSONArray(var_jsColorString);


                Element sefrTrim = li.getElementsByClass("sefr-trim").get(0);
                Element sefrCompany = li.getElementsByClass("sefr-company").get(0);
                Element sefrTime = li.getElementsByClass("sefr-time").get(0);
                Element sefrbazaar = li.getElementsByClass("sefr-bazaar").get(0);
                Element price = li.getElementsByClass("sefr-price").get(0);
                String p = price.html().replaceAll(",", "");

                Element sefrcurrency = li.getElementsByClass("sefr-currency").get(0);

                ArrayList<String> prices = new ArrayList<>();
                prices.add(p);

                ArrayList<Long> times = new ArrayList<>();
                times.add(timeMillis);


                String uniqId = modelName.text() + sefrTrim.text();
                Optional<Car> carbyname = carRepository.findCarByUniqId(uniqId);
                if (carbyname.isPresent()) {
                    Car car1 = carbyname.get();
                    car1.getPrice().add(p);
                    car1.getTimes().add(timeMillis);
                    carRepository.save(car1);
                } else {

                    Car car = new Car().setName(modelName.text())
                            .setEngName(href.split("https://bama.ir/price/")[1])
                            .setBrand(brand)
                            .setPriceInWhat(sefrbazaar.text())
                            .setPrice(prices)
                            .setTimes(times)
                            .setYear(sefrTrim.text())
                            .setLinkpage(href)
                            .setUniqId(uniqId);
                    carRepository.save(car);
                }


            }


        }

        return carRepository.findAll();
    }


}
