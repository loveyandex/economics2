package com.god.economics.crawllers.stocks.mofid;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//@SpringBootApplication
public class SarkhatApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SarkhatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String isin = "IRO3TPEZ0001";

        isin="IRO1HFRS0001";//حفارس
        isin="IRO3BDYZ0001";//دی
        isin="IRO1TAMN0001";//شستا


        isin="IRO1BRKT0001";//برکت

        isin="IRO1KSHJ0001";//hekeshti
        isin="IRO1TAMN0001";//شستا
        isin="IRO3BDYZ0001";//day

//        ArrayList<String> isins = new ArrayList<>();
//        ArrayList<String> ords = new ArrayList<>();
//        ArrayList<String> prcs = new ArrayList<>();
//        isins.add("IRO1BRKT0001");
//        isins.add("IRO1KSHJ0001");
//
//        ords.add("1300");
//        ords.add("730");
//
//        prcs.add("40880");
//        prcs.add("74570");



            String orderCount = "604";
        String orderPrice = "70186";

        String req = "";
        req = "{\"IsSymbolCautionAgreement\":false,\"CautionAgreementSelected\":false,\"IsSymbolSepahAgreement\":false,\"SepahAgreementSelected\":false,\"orderCount\":"+orderCount+",\"orderPrice\":"+orderPrice+",\"FinancialProviderId\":1,\"minimumQuantity\":\"\",\"maxShow\":0,\"orderId\":0,\"isin\":\""+isin+"\",\"orderSide\":65,\"orderValidity\":74,\"orderValiditydate\":null,\"shortSellIsEnabled\":false,\"shortSellIncentivePercent\":0}";

        String cocike;
        cocike = "GuidedTourVersion=1; SiteVersion=3.7.4; _ga=GA1.2.2099735577.1580370223; crisp-client%2Fsession%2Fe95056ad-2681-452d-976d-0c2a304165c9=session_413b3d4a-1eaa-47f4-b140-039b337e9ac7; _gid=GA1.2.1843848393.1596221132; lastmessage-4=1; lastmessage-6=1; text0_-1309020272=true; text0_1474283619=true; silverse=x4krzxfxghvmi0rn0qqjkboq; Token=6f66ab8e-bc14-4be2-9481-5e252a8e1c04; .ASPXAUTH=C58B7526DBBF96CB49FBDD5FA64EFF6DB5850FDDD87DF2C7E74B297A9B2C632D85AA6901CAB7E6B1ED5710E0D32037EC4684CAAFEF79F192432EAD77D7684AF1D3FADF5DAD9B3837918FF7FEB6D48248A37F9E7903577C046501D7166CBD72B85698E3D689BEDDE063E8CE42C7473789EE4F3D902FD9816A1CDFD9A7425874FA";



        String path = "https://onlineplus.mofidonline.com/Customer/SendOrder";
        int k = 0;
        long millis = System.currentTimeMillis();


        LocalDateTime now = LocalDateTime.now().plusDays(0);

        String myDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))+" 08:29:57.555";
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
            if (l >= timeorder)
            {
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
        httpPost.setHeader("cookie", cockie);
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