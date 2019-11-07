package com.god.economics.controllers;

import com.god.economics.models.BitcoinResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created By aMIN on 7/24/2019 5:06 PM
 */

@Controller
public class BigCOntroller {

    @RequestMapping("/cx")
    @ResponseBody
    public List<BitcoinResponse> f(Model model) {
        OkHttpClient client = new OkHttpClient();
        ArrayList<BitcoinResponse> bitcoinResponses = new ArrayList<>();


        String url = "https://min-api.cryptocompare.com/data/histohour?fsym=BTC&tsym=USD&limit=2000&api_key=67d8346761e726d36e30efb25b9450917207f937bb3ad5f1496a42b383970ca5";
        Request request = new Request.Builder()
                .url(url)
                .build();

        JSONObject jsonObject1 = new JSONObject();
        JsonArray jsonElements = new JsonArray();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);

            JSONArray data = jsonObject.getJSONArray("Data");
            int length = data.length();

            for (int i = 0; i < length; i++) {
                JSONObject object = data.getJSONObject(i);
                long time = object.getLong("time");
                double close = object.getDouble("close");
                double open = object.getDouble("open");
                Date date = new Date((long) time * 1000);
                String x = new Gson().toJson(date);

                BitcoinResponse json = new BitcoinResponse().setDate(date)
                        .setOpen(open).setPrice(close).setUnixTime(time);

                bitcoinResponses.add(json);

            }
            return bitcoinResponses;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/day")
    @ResponseBody
    public List<BitcoinResponse> fo() {
        OkHttpClient client = new OkHttpClient();
        ArrayList<BitcoinResponse> bitcoinResponses = new ArrayList<>();


        String url = "https://min-api.cryptocompare.com/data/histoday?fsym=BTC&tsym=USD&limit=2000&api_key=67d8346761e726d36e30efb25b9450917207f937bb3ad5f1496a42b383970ca5";
        Request request = new Request.Builder()
                .url(url)
                .build();

        JSONObject jsonObject1 = new JSONObject();
        JsonArray jsonElements = new JsonArray();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);

            JSONArray data = jsonObject.getJSONArray("Data");
            int length = data.length();

            for (int i = 0; i < length; i++) {
                JSONObject object = data.getJSONObject(i);
                long time = object.getLong("time");
                double close = object.getDouble("close");
                double open = object.getDouble("open");
                Date date = new Date((long) time * 1000);
                String x = new Gson().toJson(date);

                BitcoinResponse json = new BitcoinResponse().setDate(date)
                        .setOpen(open).setPrice(close).setUnixTime(time);

                bitcoinResponses.add(json);

            }
            return bitcoinResponses;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/cc")
    @ResponseBody
    public String cf() {
        return "chart";
    }


    /***
     * [
     *   [
     *     1499040000000,      // Open time
     *     "0.01634790",       // Open
     *     "0.80000000",       // High
     *     "0.01575800",       // Low
     *     "0.01577100",       // Close
     *     "148976.11427815",  // Volume
     *     1499644799999,      // Close time
     *     "2434.19055334",    // Quote asset volume
     *     308,                // Number of trades
     *     "1756.87402397",    // Taker buy base asset volume
     *     "28.46694368",      // Taker buy quote asset volume
     *     "17928899.62484339" // Ignore.
     *   ]
     * ]
     * @return
     */

    @RequestMapping("/5m")
    @ResponseBody
    public ArrayList<Double> king() {
        OkHttpClient client = new OkHttpClient();
        ArrayList<BitcoinResponse> bitcoinResponses = new ArrayList<>();
        ArrayList<Double> difopenAndClose = new ArrayList<>();


        String url = "https://api.binance.com/api/v1/klines?symbol=BTCUSDT&interval=5m";


        Request request = new Request.Builder()
                .url(url)
                .build();
        JsonParser parser = new JsonParser();
        JSONObject jsonObject1 = new JSONObject();
        try (Response response = client.newCall(request).execute()) {

            String string = response.body().string();

            JsonElement tradeElement = parser.parse(string);
            JsonArray trades = tradeElement.getAsJsonArray();
            for (JsonElement trade : trades) {
                JsonArray tradetails = trade.getAsJsonArray();
                BigInteger asBigInteger = tradetails.get(0).getAsBigInteger();
                double open = tradetails.get(1).getAsDouble();
                double close = tradetails.get(4).getAsDouble();
                difopenAndClose.add((close - open));
            }


            System.out.println(string);


            return difopenAndClose;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


//
//    @GetMapping("/")
//    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "index";
//    }

}
