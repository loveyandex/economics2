package com.god.economics;

import com.god.economics.crawllers.instagram.models.InstaUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */
@Repository
public interface InstaUserRepo extends MongoRepository<InstaUser, String> {

}
