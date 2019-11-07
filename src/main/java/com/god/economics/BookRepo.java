package com.god.economics;

import com.god.economics.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

public interface BookRepo extends MongoRepository<Book, String> {
    List<Book> findByTitle(String title);

}
