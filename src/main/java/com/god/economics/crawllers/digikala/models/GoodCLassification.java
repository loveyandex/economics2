package com.god.economics.crawllers.digikala.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * created By gOD on 11/3/2019 7:54 PM
 */

@Getter
@Setter
@Accessors(chain = true)
public class GoodCLassification {
    @Id
    private String id;
    private String name;
    private String link;
    private List<GoodCLassification> subGoodCLassificationList;



    public GoodCLassification(String name, String link) {
        this.name = name;
        this.link = link;
    }


}
