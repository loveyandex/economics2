package com.god.economics.crawllers.stocks.tgju.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * created By gOD on 1/19/2020 8:22 PM
 */

@Getter
@Setter
@Accessors(chain = true)
public class Stock {

    @Id
    private long id;
    private List<SubStock> subStock = new ArrayList<>();
}
