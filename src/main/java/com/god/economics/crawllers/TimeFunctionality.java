package com.god.economics.crawllers;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class TimeFunctionality {


    private static TimeFunctionality timeFunctionality = new TimeFunctionality();
    private LocalDateTime now;

    public LocalDateTime getNow() {
        return now;
    }

    public static TimeFunctionality getInstance() {
        return timeFunctionality;
    }

    private TimeFunctionality() {
    }


    public LocalDateTime addDays(long days) {
        now = LocalDateTime.now();
        return now.plus(days, ChronoUnit.DAYS);
    }

    public LocalDateTime addHour(long h) {
        now = LocalDateTime.now();
        return now.plus(h, ChronoUnit.HOURS);
    }


}



