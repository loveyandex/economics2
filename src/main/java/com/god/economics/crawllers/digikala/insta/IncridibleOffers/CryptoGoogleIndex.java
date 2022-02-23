package com.god.economics.crawllers.digikala.insta.IncridibleOffers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Abolfazl
 */
public class CryptoGoogleIndex {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost/wordpress/wp-json/wc/v3/products?oauth_consumer_key=ck_a266803c336f2b6c3da3c1c269a2f8139233bef5&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1644843057&oauth_nonce=l58RHLEbcz3&oauth_version=1.0&oauth_signature=MaYwAPM2WL3g1%2BOWMIadZCW4mTM%3D")
                .method("GET", null)
                .addHeader("Cookie", "digits_countrycode=93")
                .build();
        Response response = client.newCall(request).execute();





        String coin="Anchor+Protocol";
        String q=coin+"&sourceid=chrome&ie=UTF-8";
        String url = "https://www.google.com/search?q=" +  q  ;

         Document document = Jsoup.connect(url).get();

        Element result_stats = document.getElementById("result-stats");

        System.out.println();

    }

}
