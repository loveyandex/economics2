package com.god.economics;

import com.god.economics.models.Person;
import com.god.economics.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class EconomicsApplication {

    @Autowired
    private PersonRepo personRepo;

    @GetMapping("/persons")
    public List<Person> p() {
//        personRepo.save(new Person("", "king", "",12));
        List<Person> all = personRepo.findAll();
        return all;
    }


    @Autowired(required = false)
    private ProductRepo productRepo;

    public static void main(String[] args) {
        SpringApplication.run(EconomicsApplication.class, args);
    }

    //    @Bean
    public void getdata() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                Element body = Jsoup.connect("https://www.yjc.ir/fa/pricefood/Meat").get().body();
                Elements elementsByClass = body.getElementsByClass("pr-g-title");
                Elements priceinfos = body.getElementsByClass("pr-g-info");
                for (int i = 0; i < elementsByClass.size(); i = i + 2) {
                    Element byClass = elementsByClass.get(i);
                    Element pr = priceinfos.get(i);
                    String name = byClass.text();
                    System.out.println(name);
                    System.out.println(pr.text());
                    String price = pr.text();
                    long l = System.currentTimeMillis() / 1000L;

                    String s = price.replaceAll("قیمت مصوب:", "")
                            .replaceAll("تومان", "")
                            .replaceAll(" ", "")
                            .replaceAll(",", "")
                            .replaceAll("قیمت", "")
                            .replaceAll("مصوب", "")
                            .replaceAll(":", "");


                    double e = Double.parseDouble(s);

                    List<Product> products = productRepo.findByName(name);
                    if (products.size() > 0) {

                        for (Product product : products) {
                            List<Long> times = product.getTimes();
                            times.add(l);
                            List<Double> prices = product.getPrices();
                            prices.add(e);
                            product.setPrices(prices)
                                    .setTimes(times);
                            productRepo.save(product);
                        }

                    } else {

                        Product product = new Product().setName(name)
                                .setPrices(new ArrayList<Double>() {{
                                    add(e);
                                }})
                                .setTimes(new ArrayList<Long>() {{
                                    add(l);
                                }});
                        productRepo.save(product);

                    }


                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });

        if (Math.random() > 0.5) {
            thread.start();
        } else
            System.err.println("random god didn't allow meat price be gathered");

    }
}
