package com.god.economics;

import com.god.economics.crawllers.instagram.comment.models.InstaUser;
import com.god.economics.crawllers.instagram.comment.models.follow.FollowingFollower;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * created By aMIN on 7/7/2019 6:42 PM
 */

public interface FollowingFollowersRepository extends MongoRepository<FollowingFollower, String> {

    Optional<FollowingFollower> findFollowingFollowerBy(String followingUserId);


}
