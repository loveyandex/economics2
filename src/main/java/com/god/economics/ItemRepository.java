package com.god.economics;

import com.god.economics.crawllers.digikala.models.GoodCLassification;
import com.god.economics.crawllers.digikala.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

public interface ItemRepository extends MongoRepository<Item, Integer> {

    List<Item> findByName(String name);

}
