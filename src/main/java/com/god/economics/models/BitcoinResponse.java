package com.god.economics.models;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class BitcoinResponse {

    private Date date;
    private double price;
    private double open;
    private long unixTime;
}
