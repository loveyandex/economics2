package com.god.economics;

import com.god.economics.crawllers.instagram.models.InstaUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

public interface IinstaUserRepo extends MongoRepository<InstaUser, String> {

}
