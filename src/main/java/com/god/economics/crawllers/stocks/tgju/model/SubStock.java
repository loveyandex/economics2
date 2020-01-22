package com.god.economics.crawllers.stocks.tgju.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * created By gOD on 1/19/2020 8:22 PM
 */

@Getter
@Setter
@Accessors(chain = true)
public class SubStock {

    private long time;
    private double price;

}
