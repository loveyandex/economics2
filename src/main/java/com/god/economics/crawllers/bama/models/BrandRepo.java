package com.god.economics.crawllers.bama.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * created By gOD on 12/16/2019 12:32 AM
 */

public interface BrandRepo extends MongoRepository<Brand, String> {

    List<Brand> findByName(String name);

}
