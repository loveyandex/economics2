package com.god.economics.crawllers.digikala;

import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Accessors(chain = true)

public class Cat {

    public int id;
    public int parentId;
    public String name;
//    public List<Cat> subcats;

}
