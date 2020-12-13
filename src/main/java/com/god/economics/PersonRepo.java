package com.god.economics;

import com.god.economics.models.Person;
import com.god.economics.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

@Repository
public interface PersonRepo extends MongoRepository<Person, String> {

}
