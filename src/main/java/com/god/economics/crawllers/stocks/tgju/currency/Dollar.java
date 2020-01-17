package com.god.economics.crawllers.stocks.tgju.currency;

import com.god.economics.crawllers.Reqs;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * created By gOD on 1/17/2020 11:19 PM
 */
@RestController
public class Dollar {



    @GetMapping("/dollar")
    public JSONArray index() {
        try {
            String resp = Reqs.getReq("https://www.tgju.org/chart-summary-ajax/price_dollar_rl");
            JSONArray objects = new JSONArray(resp);
            System.out.println(objects.length());
            for (Object object : objects) {
                Object o = ((JSONArray) object).get(0);
                System.out.println(o);

            }

            return objects.getJSONArray(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;}
}
