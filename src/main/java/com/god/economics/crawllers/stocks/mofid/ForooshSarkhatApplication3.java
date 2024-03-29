package com.god.economics.crawllers.stocks.mofid;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

//@SpringBootApplication
public class ForooshSarkhatApplication3 implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ForooshSarkhatApplication3.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String isin = "IRO3TPEZ0001";

        isin = "IRO3BDYZ0001";//

        String orderCount = "4000";
        String orderPrice = "50748";

        String req = "";
        req = "{\"IsSymbolCautionAgreement\":false,\"CautionAgreementSelected\":false,\"IsSymbolSepahAgreement\":false,\"SepahAgreementSelected\":false,\"orderCount\":" + orderCount + ",\"orderPrice\":" + orderPrice + ",\"FinancialProviderId\":1,\"minimumQuantity\":0,\"maxShow\":0,\"orderId\":0,\"isin\":\"" + isin + "\",\"orderSide\":\"86\",\"orderValidity\":74,\"orderValiditydate\":null,\"shortSellIsEnabled\":false,\"shortSellIncentivePercent\":0}";

        String cocike;
        cocike = "authorization: BasicAuthentication 74511c37-6098-4cf5-adbb-25525f838bcf";


        String path = "https://api2.mofidonline.com/Web/V1/Order/Post";
        int k = 0;
        long millis = System.currentTimeMillis();


        LocalDateTime now = LocalDateTime.now().plusDays(0);

        String myDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " 08:29:57.555";
        LocalDateTime localDateTime = LocalDateTime.parse(myDate,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
/*
  With this new Date/Time API, when using a date, you need to
  specify the Zone where the date/time will be used. For your case,
  seems that you want/need to use the default zone of your system.
  Check which zone you need to use for specific behaviour e.g.
  CET or America/Lima
*/
        long timeorder = localDateTime
                .atZone(ZoneId.systemDefault())
                .toInstant().toEpochMilli();


        //test correct of connection
        for (int i = 0; i < 5; i++) {

            String buystock = buystock(path, cocike, req);
            System.out.println(buystock);
        }


        while (true) {
            long l = System.currentTimeMillis();
            if (l >= timeorder) {
                String buystock = buystock(path, cocike, req);
                System.out.println(buystock);


//                Thread.sleep(200);

            }


            long x = l - millis;
            System.out.println(x);
            millis = l;
//            if (((boolean) new JSONObject("{'name':false}").get("name"))) {
//                break;
//            }

            Thread.sleep(2);


        }

    }

    public static String buystock(String uri, String cockie, String jsonBody) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

//        String uri = "https://www.instagram.com/p/"+shortcode+"/";

        HttpPost httpPost = new HttpPost(uri);

//        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("content-type", "application/json");
        httpPost.setHeader("authorization", "BasicAuthentication 74511c37-6098-4cf5-adbb-25525f838bcf");
        httpPost.setHeader("user-agent", "Mozilla/6.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
        httpPost.setHeader("origin", "https://onlineplus.mofidonline.com");
        httpPost.setHeader("referer", "https://onlineplus.mofidonline.com/Home/Default/page-1");
        httpPost.setHeader("sec-fetch-dest", "empty");
        httpPost.setHeader("sec-fetch-mode", "cors");
        httpPost.setHeader("sec-fetch-site", "same-origin");
        httpPost.setEntity(new StringEntity(jsonBody));
        CloseableHttpResponse response = client.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        response.getEntity().getContentLength();  //it should not be 0
        System.out.println(uri);

        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();

    }


}