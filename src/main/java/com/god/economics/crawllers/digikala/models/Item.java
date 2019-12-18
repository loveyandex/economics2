package com.god.economics.crawllers.digikala.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * created By gOD on 11/18/2019 7:49 PM
 */


@Getter
@Setter
@Accessors(chain = true)
public class Item {
    private Integer id;
    private String name;
    private String category;
    private String brand;
    private String varient;
    private int price;
    private ArrayList<Integer> prices=new ArrayList<>();
    private ArrayList<Long> times = new ArrayList<>();
    private int quantity;


}
