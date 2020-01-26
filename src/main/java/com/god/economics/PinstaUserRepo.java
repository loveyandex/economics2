package com.god.economics;

import com.god.economics.crawllers.instagram.comment.models.PinstaUser;
import com.god.economics.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

public interface PinstaUserRepo extends MongoRepository<PinstaUser, String> {

}
