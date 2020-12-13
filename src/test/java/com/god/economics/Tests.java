package com.god.economics;


import com.god.economics.crawllers.TimeFunctionality;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

/**
 * problem is the consider of all possible rules in datetime adding such as
 * difference of months's days and leap years(February 29 in leap years), we solve it with getting absolute reference for time
 * i choose for instance 5 years adding time to consider all special rules mentioned above
 */
class Tests {

    TimeFunctionality timeFunctionality = TimeFunctionality.getInstance();
    ZoneId ref = ZoneId.of("America/Los_Angeles");

    @Test
    void additionDay() {
        //leap years  considered
        long days = -5 * 366;
        long daystomilisec = days * 24 * 60 * 60 * 1000;
        LocalDateTime addedlocalDateTime = this.timeFunctionality.addDays(days);
        LocalDateTime now = this.timeFunctionality.getNow();

        ZonedDateTime zdt = now.atZone(ref);
        ZonedDateTime zdtadded = addedlocalDateTime.atZone(ref);

        assertEquals(zdt.toInstant().toEpochMilli()
                , zdtadded.toInstant().toEpochMilli() - daystomilisec);
    }

    @Test
    void additionHour() {
        //leap years  considered
        long hs = 51 * 24 * 366;
        long hrstomilisec = hs * 60 * 60 * 1000;

        LocalDateTime addedlocalDateTime = timeFunctionality.addHour(hs);
        LocalDateTime now = timeFunctionality.getNow();

        //transfer two DateTimes to a same reference to validate
        ZonedDateTime zdt = now.atZone(ref);
        ZonedDateTime zdtadded = addedlocalDateTime.atZone(ref);

        assertEquals(zdt.toInstant().toEpochMilli()
                , zdtadded.toInstant().toEpochMilli() - hrstomilisec);
    }


}