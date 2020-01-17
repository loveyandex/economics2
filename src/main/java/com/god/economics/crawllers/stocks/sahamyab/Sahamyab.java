package com.god.economics.crawllers.stocks.sahamyab;

import com.god.economics.crawllers.Reqs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * created By gOD on 12/20/2019 2:03 PM
 */

@RestController
public class Sahamyab {


    @GetMapping("/sahamhis/{t1}/{t2}")
    public String error(@PathVariable(name = "t1") String t1, @PathVariable(name = "t2") String t2) throws IOException {
        String url = String.format("http://www.sahamyab.com/guest/tradingview/history?adjustment=&symbol=%s&resolution=D&from=%s&to=%s","%D9%88%D8%AA%D8%AC%D8%A7%D8%B1%D8%AA", t1, t2);

        System.out.println(url);
        String resp = Reqs.getReq(url);
        return resp;


    }
}
