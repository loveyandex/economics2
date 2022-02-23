package com.god.economics.crawllers.digikala.logics.atr;

import com.github.instagram4j.instagram4j.IGClient;
import com.god.economics.crawllers.digikala.logics.Requests;
import com.god.economics.crawllers.instagram.login.LoginWith;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import static com.god.economics.crawllers.digikala.incredibleOffers2.igClient;

/**
 * @author Abolfazl
 */
public class Search implements Requests<AtrResponse> {


    @Override
    public String url() {
        String s = "https://www.digikala.com/search/category-perfume/?pageno=2&sortby=4";
        s = "https://www.digikala.com/search/category-perfume/?q=%D8%B2%D9%86%D8%A7%D9%86%D9%87&sortby=22&page=3";


        return s;
    }

    @Override
    public AtrResponse apply() throws IOException, ClassNotFoundException {
        String url = this.url();
        String ua = "Mozilla/5.0 ;Windows NT 6.1; "
                + "WOW64; AppleWebKit/537.36 ;KHTML, like Gecko; "
                + "Chrome/39.0.2171.95 Safari/537.36";
        Connection connect = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36").userAgent(ua);

        String takhfifelon = "impresso_atr";
        String godisgreat = "godisgreat";
        IGClient igClient = LoginWith.loginwithusernamepass(takhfifelon, godisgreat, takhfifelon);

        try {

            Element body = connect.get().body();
            Element aClass = body.getElementsByAttributeValue("class", "c-listing__items js-plp-products-list").get(0);


            List<Node> nodes = aClass.childNodes();

            IntStream
                    .range(0, nodes.size())
                    .mapToObj(nodes::get)
                    .forEach(node -> {
                        Element div = ((Element) node).getElementsByTag("div").get(0);
                        String attr = div.attr("data-enhanced-ecommerce");
                        Elements imgds = div.getElementsByTag("img");

                        Element img = imgds.stream().filter(element -> !element.attr("src").contains(".svg")).findFirst().orElseThrow(() -> {
                            System.out.println();
                            return null;
                        });

                        String src = img.attr("src")/*.split(".jpg")[0] + ".jpg"*/;

                        DataEnhancedEcommerce dataEnhancedEcommerce = new Gson().fromJson(attr, DataEnhancedEcommerce.class);
                        dataEnhancedEcommerce.setSrc(src);


                        BufferedImage bufferedImageu = null;

                        try {
                            bufferedImageu = ImageIO.read(new URL(src));
                        } catch (IOException e) {
                            return ;
                        }

                        File output = new File("output.jpg");

                        try {
                            ImageIO.write(bufferedImageu, "jpg", output);
                        } catch (IOException e) {
                            return ;
                        }
                        String direct = "برای خرید و جزییات قیمت همین پست رو برامون دایرکت کنین 💖💖 در کسری از ثانیه با شما گفتگو خواهد شد  ";

                        String t = dataEnhancedEcommerce.getName() + "\n\n" + direct + "\n\n" + "#" + dataEnhancedEcommerce.getName().replaceAll(" ", "_") + "\n";
                        String[] s = dataEnhancedEcommerce.getName().split(" ");
                        if (s.length > 1)
                            t += "#" + s[0] + "_" + s[1] + "\n";
                        if (s.length > 2)
                            t += "#" + s[0] + "_" + s[1] + "_" + s[2] + "\n";

                        for (int j = 0; j < 5 && j < s.length; j++) {
                            String s1 = s[j];
                            String s2 = "#" + s1 + "\n";
                            t = t + s2;
                        }

                        t = t + "#impresso_atr" + "\n";
                        t = t + "#impresso" + "\n";
                        t = t + "#atr" + "\n";
                        t = t + "#" + "عطر" + "\n";
                        t = t + "#" + "عطرعشق" + "\n";
                        t = t + "#" + "عطرمردانه" + "\n";
                        t = t + "#" + "عطرزنانه" + "\n";
                        t = t + "#" + "عطر_زنانه" + "\n";
                        t = t + "#" + "عطروادکلن" + "\n";
                        t = t + "#" + "عطر_مردانه" + "\n";
                        t = t + "#" + "عطر_اورجینال" + "\n";
                        t = t + "#" + "عطر_اصل" + "\n";
                        t = t + "#" + "ادکلن" + "\n";
                        t = t + "#" + "ادو" + "\n";
                        t = t + "#" + "زنانه" + "\n";
                        t = t + "#" + "اکسسوری_زنانه" + "\n";
                        t = t + "#" + "اکسسوری_مردانه" + "\n";
                        t = t + "#" + "ادوکلن" + "\n";
                        t = t + "#" + "تخفیفان" + "\n";
                        t = t + "#" + "تخفیفدار" + "\n";

                        try {
                            igClient.getActions().timeline().uploadPhoto(output, t
                            ).get();
                        } catch (InterruptedException e) {
                            return ;
                        } catch (ExecutionException e) {
                            return ;

                        }
                        try {
                            Thread.sleep(1000 * 60 * 9);
                        } catch (InterruptedException e) {
                            return ;
                        }


                        System.out.println(dataEnhancedEcommerce.getName() + dataEnhancedEcommerce.getSrc());
                    });


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public AtrResponse paging() {
        return null;
    }


    public static void main(String[] args) {
        try {
            new Search().apply();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
