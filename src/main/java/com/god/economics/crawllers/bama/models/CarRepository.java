package com.god.economics.crawllers.bama.models;

import com.god.economics.crawllers.digikala.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

public interface CarRepository extends MongoRepository<Car, Integer> {

    List<Car> findByName(String name);
    Optional<Car> findCarByUniqId(String name);

}
