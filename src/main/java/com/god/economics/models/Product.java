package com.god.economics.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.sql.Time;
import java.util.List;


/**
 * created By aMIN on 7/7/2019 6:06 PM
 */

@Getter
@Setter
@Accessors(chain = true)
public class Product {

    @Id
    private String id;
    private String username;
    private String name;
    private List<Double> prices;
    private List<Long> times;

}
