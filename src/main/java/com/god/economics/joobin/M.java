package com.god.economics.joobin;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * created By aMIN on 5/31/2019 1:42 AM
 */

public class M {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://min-api.cryptocompare.com/data/histohour?fsym=BTC&tsym=USD&limit=2000&api_key=67d8346761e726d36e30efb25b9450917207f937bb3ad5f1496a42b383970ca5";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            JSONObject jsonObject = new JSONObject(string);

            JSONArray data = jsonObject.getJSONArray("Data");
            int length = data.length();


            FileWriter fileWriter = new FileWriter("btc_hours52.csv");

            for (int i = 0; i < length; i++) {
                JSONObject object = data.getJSONObject(i);
                long time = object.getLong("time");
                double close = object.getDouble("close");
                System.out.println(time + " : " + close);
                Date date = new Date((long) time * 1000);
                String x = new Gson().toJson(date);
                System.out.println(x);
                fileWriter.append(x + "," + time + "," + close + "\n");
                fileWriter.flush();
            }
            System.exit(0);


            JSONObject objecct = data.getJSONObject(0);
            long timed = objecct.getLong("time");
            long tots = timed - 3600;

            System.err.println(jsonObject.getLong("TimeFrom"));

            int t = 38;
            while (t > 0) {
                try {
                    String url2 = url + "&toTs=" + tots;
                    System.out.println(tots);

                    Request req = new Request.Builder()
                            .url(url2)
                            .build();
                    t--;

                    Response res = client.newCall(req).execute();
                    String d = res.body().string();
                    JSONObject jd = new JSONObject(d);

                    data = jd.getJSONArray("Data");


                    fileWriter = new FileWriter("btc_hours44_" + t + ".csv");

                    for (int i = 0; i < length; i++) {
                        JSONObject object = data.getJSONObject(i);
                        long time = object.getLong("time");
                        double close = object.getDouble("close");
                        Date date = new Date((long) time * 1000);
                        String x = new Gson().toJson(date);
                        fileWriter.append(x + "," + time + "," + close + "\n");
                        fileWriter.flush();
                    }


                    long gg = jd.getLong("TimeFrom");
                    System.err.println(jd.getLong("TimeTo") - tots);
                    tots = gg - 3600;

                    System.out.println(jd.getLong("TimeTo") + " "
                            + jd.getLong("TimeFrom"));

                } catch (Exception e) {
                    System.out.println(e);
                }
            }


            fileWriter.close();


            System.out.println(new Gson().toJson(data));


            JSONObject obj = new JSONObject();

            obj.put("name", "foo");
            obj.put("num", new Integer(100));
            obj.put("balance", new Double(1000.21));
            obj.put("is_vip", new Boolean(true));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
