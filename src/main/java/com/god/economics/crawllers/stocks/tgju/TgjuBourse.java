package com.god.economics.crawllers.stocks.tgju;

import com.god.economics.StockTgjuRepository;
import com.god.economics.crawllers.stocks.tgju.model.Stock;
import com.god.economics.crawllers.stocks.tgju.model.SubStock;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * created By gOD on 12/14/2019 9:12 PM
 */

@RestController
public class TgjuBourse {




    @Autowired
    private StockTgjuRepository stockTgjuRepository;






    @GetMapping("/god")
    public String error() throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://call.tgju.org/stocks/all.json";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }

    @GetMapping("/mod")
    public String s() throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "http://call2.tgju.org/ajax.json";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }

    @GetMapping("/nod")
    public String ssdsd() throws IOException {

        OkHttpClient client = new OkHttpClient();

        String url = "https://api.tgju.online/v1/stocks/instrument/history-data/" +
                "63917421733088077?order_dir=desc&market=stock&draw=2&columns%5B0%5D%5Bdata%5D=0&" +
                "columns%5B0%5D%5Bname%5D=&columns%5B0%5D%5Bsearchable%5D=true&columns%5B0%5D%5Borderable%5D=false&columns%5B0%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B0%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B1%5D%5Bdata%5D=1&columns%5B1%5D%5Bname%5D=&columns%5B1%5D%5Bsearchable%5D=true&columns%5B1%5D%5Borderable%5D=false&columns%5B1%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B1%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B2%5D%5Bdata%5D=2&columns%5B2%5D%5Bname%5D=&columns%5B2%5D%5Bsearchable%5D=true&columns%5B2%5D%5Borderable%5D=false&columns%5B2%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B2%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B3%5D%5Bdata%5D=3&columns%5B3%5D%5Bname%5D=&columns%5B3%5D%5Bsearchable%5D=true&columns%5B3%5D%5Borderable%5D=false&columns%5B3%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B3%5D%5Bsearch%5D%5Bregex%5D=false&columns%5B4%5D%5Bdata%5D=4&columns%5B4%5D%5Bname%5D=&columns%5B4%5D%5Bsearchable%5D=true&columns%5B4%5D%5Borderable%5D=false&columns%5B4%5D%5Bsearch%5D%5Bvalue%5D=&columns%5B4%5D%5Bsearch%5D%5Bregex%5D=false&start=30&length=30&search%5Bvalue%5D=&search%5Bregex%5D=false&_=1578869060671";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }


    }

    @GetMapping("/stock/{id}")
    public ArrayList<Object> stcoek(@PathVariable String id) throws IOException {

        Document document = Jsoup.connect("https://bourse.tgju.org/markets/stock/" + id + "/history").get();

        String data = document.html().split("\\(\"#chart-area-history\"\\).msHighcharts\\(\\{\n" + "\t\t\t\tchartData:")[1].split("chartType: \"area\"")[0].split("]],")[0] + "]]";

        Stock stock = new Stock().setId(Long.parseLong(id));


        JSONArray timeandprices = new JSONArray(data);
        ArrayList<Object> sts = new ArrayList<>();
        for (int i = 0; i < timeandprices.length(); i++) {
            ArrayList<Object> ds = new ArrayList<>();

            long time = (long) ((JSONArray) timeandprices.get(i)).get(0);
            double price = ((Number) ((JSONArray) timeandprices.get(i)).get(1)).doubleValue();
//            System.out.println(time + "   " + price);
            ds.add(time);
            ds.add(price);
            sts.add(ds);

            stock.getSubStock().add(new SubStock().setPrice(price).setTime(time));
        }
        stockTgjuRepository.save(stock);
        return sts;
    }

    @GetMapping("/stockweekdays/{id}")
    public ArrayList<Object> stock(@PathVariable String id) throws IOException {

        Document document = Jsoup.connect("https://bourse.tgju.org/markets/stock/" + id + "/history").get();

        String data = document.html().split("\\(\"#chart-area-history\"\\).msHighcharts\\(\\{\n" + "\t\t\t\tchartData:")[1].split("chartType: \"area\"")[0].split("]],")[0] + "]]";

        Stock stock = new Stock().setId(Long.parseLong(id));


        JSONArray timeandprices = new JSONArray(data);
        ArrayList<Object> sts = new ArrayList<>();
        for (int i = 0; i < timeandprices.length(); i++) {
            ArrayList<Object> ds = new ArrayList<>();

            long time = (long) ((JSONArray) timeandprices.get(i)).get(0);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            c.setTimeZone(TimeZone.getTimeZone("GMT+430"));
//            int hour = c.get(Calendar.HOUR_OF_DAY);
//            int minutes = c.get(Calendar.MINUTE);
            int month = c.get(Calendar.MONTH);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            int dayofmonth = c.get(Calendar.DAY_OF_MONTH);




            double price = ((Number) ((JSONArray) timeandprices.get(i)).get(1)).doubleValue();
//            System.out.println(time + "   " + price);
            ds.add(time);
            ds.add(price);
            ds.add(month);
            ds.add(dayofmonth);
            ds.add(dayOfWeek%7);

            sts.add(ds);

            stock.getSubStock().add(new SubStock().setPrice(price).setTime(time));
        }
        stockTgjuRepository.save(stock);
        return sts;
    }


}