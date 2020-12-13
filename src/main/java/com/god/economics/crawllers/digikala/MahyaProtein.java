package com.god.economics.crawllers.digikala;

import com.god.economics.GoodClassificationRepo;
import com.god.economics.ItemRepository;
import com.god.economics.crawllers.digikala.models.GoodCLassification;
import com.god.economics.crawllers.digikala.models.Item;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * created By gOD on 9/18/2019 1:30 AM
 */


@RestController
public class MahyaProtein {
    @Autowired
    private GoodClassificationRepo goodClassificationRepo;

    @Autowired
    private String publicInstance;

    @Autowired
    private ItemRepository itemRepository;

    private String host = "https://www.digikala.com";


    @GetMapping("/pub")
    public String test() {
        return publicInstance;
    }

    @GetMapping("/pub2")
    public String tes2t() {
        publicInstance = "king in the north" + Math.random();
        return publicInstance;
    }


    @GetMapping("/root")
    public void ss() {


        Item item1 = itemRepository.findAll().get(0);
        item1.setQuantity(1212);
        itemRepository.save(item1);


        String url = "sd";
        url = "https://www.digikala.com/product/dkp-383379";
//        url = "https://www.digikala.com/search/category-accessoeys/";
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element body = document.body();


        Elements elementsByAttribute = body.getElementsByAttribute("data-enhanced-ecommerce");
        for (Element element : elementsByAttribute) {
            String attr = element.attr("data-enhanced-ecommerce");
            Item item = new Gson().fromJson(attr, Item.class);
            itemRepository.save(item);
            System.out.println(attr);

        }


        Elements type = document.getElementsByAttributeValue("type", "application/ld+json");
        String value = ((DataNode) type.get(0).childNodes().get(0)).getWholeData();
        JSONObject jsonObject = new JSONObject(value);


        System.out.println(value);

    }

    @GetMapping("/get")
    public void king() throws IOException {
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

    @GetMapping("/moot")
    public String d() throws IOException {


        List<GoodCLassification> goods = goodClassificationRepo.findAll();

        String host = "https://www.digikala.com/";
        for (GoodCLassification good : goods) {
            String link = host + good.getLink();
            Document document = Jsoup.connect(link).get();
            ArrayList<GoodCLassification> subGoodCLassificationList = new ArrayList<>();
            for (Element elementsByClass : document.getElementsByClass("c-category-card ")) {
                String name = elementsByClass.getElementsByClass("c-category-card__title").get(0).text();
                GoodCLassification sub = new GoodCLassification(name, null);


                Elements subs = elementsByClass.getElementsByClass("c-category-card__list");
                ArrayList<GoodCLassification> subsOfsub = new ArrayList<>();
                for (Element a : subs.get(0).getElementsByTag("a")) {
                    String namesub = a.text();
                    String href = a.attr("href");
                    GoodCLassification lassification = new GoodCLassification(namesub, href);
                    if (href.contains("search/")) {
                        subsOfsub.add(lassification);
                    } else {
                        Document doc = Jsoup.connect(host + href).get();
                        Elements subcats = doc.getElementsByClass("c-catalog__list--depth");
                        ArrayList<GoodCLassification> othersubs = new ArrayList<>();
                        for (Element element : subcats.get(0).getElementsByTag("a")) {
                            String text = element.text();
                            String searchroot = element.attr("href");

                            GoodCLassification goodCLassification = new GoodCLassification(text, searchroot);
                            othersubs.add(goodCLassification);
                        }
                        lassification.setSubGoodCLassificationList(othersubs);
                        subsOfsub.add(lassification);
                    }

                }

                sub.setSubGoodCLassificationList(subsOfsub);
                subGoodCLassificationList.add(sub);


                System.out.println();


            }
            good.setSubGoodCLassificationList(subGoodCLassificationList);
            goodClassificationRepo.save(good);
        }

        return "ok";

    }


    @GetMapping("/digi")
    public String findsearchurl() throws IOException {
        List<GoodCLassification> all = goodClassificationRepo.findAll();
        for (GoodCLassification goodCLassification : all) {
            for (GoodCLassification cLassification : goodCLassification.getSubGoodCLassificationList()) {
                for (GoodCLassification lassification : cLassification.getSubGoodCLassificationList()) {

                    if (lassification.getLink().contains("/search")) {
                        if (!lassification.getLink().contains("/search/category-mobile-phone/"))
                            getProductIds(lassification.getLink());
                        else
                            System.out.println(lassification.getLink());

                    } else {
                        if (lassification.getSubGoodCLassificationList() != null) {

                            for (GoodCLassification fl : lassification.getSubGoodCLassificationList()) {
                                if (fl.getLink().contains("/search")) {
                                    getProductIds(fl.getLink());
                                } else
                                    throw new RuntimeException("why");

                            }
                        }
                    }


                }


            }

        }


        return "ok";
    }

    public void getProductIds(String link) {
        long unixtime = System.currentTimeMillis();

        try {
            String url = host + link;
            System.out.println(url);
            Document document = Jsoup.connect(url).get();


            Elements scripts = document.head().getElementsByTag("script");


            Element sc = scripts.get(scripts.size() - 3);
            String s = ((String) ((DataNode) scripts.get(scripts.size() - 5).childNodes().get(0)).getWholeData());
            int click_impression = s.indexOf("click_impression");
            int i = s.indexOf(";\n" + "                        var page_search_url");

            String substring = s.substring(click_impression, i).substring(19);
            JSONArray objects = new JSONArray(substring);
            System.out.println("objects.length() " + objects.length());

            for (int j = 0; j < objects.length(); j++) {
                JSONObject jsonObject = objects.getJSONObject(j);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");
                int price = 0;
                try {
                    price = jsonObject.getInt("price");

                } catch (org.json.JSONException e) {
                    price = 0;
//                    System.out.println(price);
                }

                Optional<Item> byId = itemRepository.findById(id);
                if (byId.isPresent()) {
                    ArrayList<Integer> prices = byId.get().getPrices();
                    if (prices != null) {
                        prices.add(price);
                        byId.get().getTimes().add(unixtime);
                    } else {
                        ArrayList<Integer> integers = new ArrayList<>();
                        ArrayList<Long> times = new ArrayList<>();
                        integers.add(price);
                        times.add(unixtime);
                        byId.get().setPrices(integers);
                        byId.get().setTimes(times);

                    }
                    itemRepository.save(byId.get());
                } else {
                    Item item = new Item().setId(id).setName(name).setPrice(price);

                    ArrayList<Integer> integers = new ArrayList<>();
                    ArrayList<Long> times = new ArrayList<>();
                    integers.add(price);
                    times.add(unixtime);
                    item.setPrices(integers);
                    item.setTimes(times);
                    itemRepository.save(item);
                }

            }


            String attr = document.getElementsByClass("c-pager__next").get(0).attr("data-page");
            Integer integer = Integer.parseInt(attr);

            for (int j = 2; j <= integer; j++) {
                String otherurl = url + "/?sortby=4&pageno=" + j;
                System.out.println(otherurl);
                document = Jsoup.connect(otherurl).get();
                scripts = document.head().getElementsByTag("script");

                sc = scripts.get(scripts.size() - 3);
                s = ((String) ((DataNode) scripts.get(scripts.size() - 5).childNodes().get(0)).getWholeData());
                click_impression = s.indexOf("click_impression");
                i = s.indexOf(";\n" + "                        var page_search_url");

                substring = s.substring(click_impression, i).substring(19);
                objects = new JSONArray(substring);
                System.out.println("objects.length() " + objects.length());

                for (int k = 0; k < objects.length(); k++) {
                    JSONObject jsonObject = objects.getJSONObject(k);
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    int price = 0;
                    try {
                        price = jsonObject.getInt("price");

                    } catch (org.json.JSONException e) {
                        price = 0;
                    }
                    Optional<Item> byId = itemRepository.findById(id);
                    if (byId.isPresent()) {
                        ArrayList<Integer> prices = byId.get().getPrices();
                        if (prices != null) {
                            prices.add(price);
                            byId.get().getTimes().add(unixtime);
                        } else {
                            ArrayList<Integer> integers = new ArrayList<>();
                            ArrayList<Long> times = new ArrayList<>();
                            integers.add(price);
                            times.add(unixtime);
                            byId.get().setPrices(integers);
                            byId.get().setTimes(times);

                        }
                        itemRepository.save(byId.get());
                    } else {
                        Item item = new Item().setId(id).setName(name).setPrice(price);

                        ArrayList<Integer> integers = new ArrayList<>();
                        ArrayList<Long> times = new ArrayList<>();
                        integers.add(price);
                        times.add(unixtime);
                        item.setPrices(integers);
                        item.setTimes(times);
                        itemRepository.save(item);
                    }
                }

                Thread.sleep(2000);


            }


        } catch (IOException | InterruptedException | IndexOutOfBoundsException e) {

            System.out.println(e.getMessage());
            ;
            return;

        }

    }


}
