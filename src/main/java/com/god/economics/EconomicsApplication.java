package com.god.economics;

import com.god.economics.models.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EconomicsApplication {

    @Autowired
    private ProductRepo productRepo;

    public static void main(String[] args) {
        SpringApplication.run(EconomicsApplication.class, args);
    }

    @Bean
    public void getdata() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                Element body = Jsoup.connect("https://www.yjc.ir/fa/pricefood/Meat").get().body();
                Elements elementsByClass = body.getElementsByClass("pr-g-title");
                Elements priceinfos = body.getElementsByClass("pr-g-info");
                for (int i = 0; i < elementsByClass.size(); i=i+2) {
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


        }).start();

    }
}
