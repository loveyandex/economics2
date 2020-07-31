package com.god.economics.crawllers.instagram.donyacomment;

import com.god.economics.crawllers.instagram.models.InstaUser;
import com.god.economics.crawllers.instagram.models.InstaUserComments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * created By gOD on 7/12/2020 7:52 AM
 */

public interface UserCommentedRepository extends MongoRepository<InstaUserComments,String> {
    boolean existsByUserid(String userid);

    Optional<InstaUserComments> findByUserid(String userid);
}
