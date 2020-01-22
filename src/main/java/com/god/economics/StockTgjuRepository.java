package com.god.economics;

import com.god.economics.crawllers.digikala.models.Item;
import com.god.economics.crawllers.stocks.tgju.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

public interface StockTgjuRepository extends MongoRepository<Stock, Integer> {

//    List<Stock> findByIdAndAndSubStock(long id, long time);

}
