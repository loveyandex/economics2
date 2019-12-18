package com.god.economics.crawllers.bama.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * created By gOD on 12/11/2019 3:22 AM
 */


@Getter
@Setter
@Accessors(chain = true)
public class Car {
    @Id
    private String id;
    private String uniqId;
    private String linkpage;
    private String year;
    private String name;
    private String engName;
    private Brand brand;
    private String priceInWhat;
    private List<String> price;
    private List<Long> times;
}
