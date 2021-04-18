package com.god.economics.crawllers.digikala;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.responses.media.MediaResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import com.god.economics.crawllers.instagram.api.story.SerializableCookieJar;
import com.god.economics.crawllers.instagram.api.story.run;
import com.god.economics.crawllers.instagram.login.LoginWith;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * created By gOD on 10/6/2020 9:12 PM
 */

public class incredibleOffers2 {

    @SneakyThrows
    public static void serialize(Object o, File to) {
        FileOutputStream file = new FileOutputStream(to);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(o);
        out.close();
        file.close();
    }

    @SneakyThrows
    public static <T> T deserialize(File file, Class<T> clazz) {
        InputStream in = new FileInputStream(file);
        ObjectInputStream oIn = new ObjectInputStream(in);

        T t = clazz.cast(oIn.readObject());

        in.close();
        oIn.close();

        return t;
    }


    public static OkHttpClient formTestHttpClient(SerializableCookieJar jar) {
        return IGUtils.defaultHttpClientBuilder().cookieJar(jar).build();
    }

    public static IGClient igClient() throws Exception {
        File to = new File("igclient.ser"), cookFile = new File("cookie.ser");

        return IGClient.from(new FileInputStream(to),
                formTestHttpClient(deserialize(cookFile, SerializableCookieJar.class)));


    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try {
            Document document = Jsoup.connect("https://www.digikala.com/incredible-offers/").get();
            Element cproductlist__content = document.getElementsByClass("c-product-list__content").get(0);

            String takhfifelon = "takhfifelon";
            String godisgreat = "godisgreat";
//            IGClient igClient = LoginWith.loginwithusernamepass(takhfifelon, godisgreat);
            IGClient igClient =igClient();



            List<Node> nodes = cproductlist__content.childNodes();

            for (int i = nodes.size()-30; i >= 0; i--) {
                Node node = nodes.get(i);
                List<Node> childNodes = node.childNodes();
                for (Node childNode : childNodes) {
                    Node node1 = childNode.childNodes().get(0);
                    String href = node1.attr("href");

                    Node node2 = childNode.childNodes().get(1);

                    Node node3 = node2.childNodes().get(1);
                    String title = node3.attr("title");
                    System.out.println(title);
                    List<Node> nodes1 = node3.childNodes();
                    if (nodes1.size()==0)
                        continue;

                    String imgsrc = nodes1.get(1).attr("src");
                    if (imgsrc=="")
                        continue;

                    Node node4 = childNode.childNodes().get(1).childNodes().get(5);
                    Element element = (Element) node4;
                    Elements del = element.getElementsByTag("del");
                    if (del.size()==0)
                        continue;

                    String delprice = del.get(0).text();
                    String oval = element.getElementsByClass("c-price__discount-oval").get(0).text();
                    String realprice = element.getElementsByClass("c-price__value-wrapper").get(0).text();


                    BufferedImage bufferedImageu = ImageIO.read(new URL(imgsrc));
                    File output = new File("output.jpg");
                    ImageIO.write(bufferedImageu, "jpg", output);
                    System.err.println("uploading ");


                    String caption0 = "نام کالا:" + "\n" + title + "\n" +
                            "قیمت اصلی: "
                            + delprice + "\n"
                            + oval +
                            " :میزان تخفیف "
                            + "\n" +
                            "قیمت با تخفیف : "
                            + realprice;


                    String t = "#" + title.replaceAll(" ", "_")+ "\n";
                    String[] s = title.split(" ");
                    if(s.length>1)
                        t += "#" + s[0] + "_" + s[1] + "\n";
                    if(s.length>2)
                        t += "#" + s[0] + "_" + s[1] +  "_" + s[2] + "\n";

                    for (String s1 : s) {
                        String s2 = "#" + s1 + "\n";
                        t = t + s2;
                    }

                    t = t + "#takhfif"+ "\n";
                    t = t + "#"+"تخفیف"+ "\n";
                    t = t +"#"+"تخفیفان"+ "\n";
                    t = t +"#"+"تخفیفدار"+ "\n";

                    String caption = caption0 + "\n\n\n\n" + t;


                    System.out.println(caption);
                    igClient.getActions().timeline().uploadPhoto(output,
                            caption).get();


                    Thread.sleep(1000*60*13);
                    System.out.println(childNode.toString());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
