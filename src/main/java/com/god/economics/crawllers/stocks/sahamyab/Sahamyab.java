package com.god.economics.crawllers.stocks.sahamyab;

import com.god.economics.crawllers.Reqs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * created By gOD on 12/20/2019 2:03 PM
 */

@RestController
public class Sahamyab {



    @GetMapping("/sahamhis")
    public String error() throws IOException {
        String url = "http://www.sahamyab.com/guest/tradingview/history?adjustment=&symbol=%D9%88%D8%AA%D8%AC%D8%A7%D8%B1%D8%AA&resolution=D&from=1420070400&to=1576793157";

        String resp = Reqs.getReq(url);
        return resp;


    }
}
