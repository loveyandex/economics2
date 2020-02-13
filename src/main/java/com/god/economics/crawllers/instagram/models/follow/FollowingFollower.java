package com.god.economics.crawllers.instagram.models.follow;

import com.god.economics.crawllers.instagram.models.InstaUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class FollowingFollower {
    @Id
    private String id;
    private String followingUserId;
    private String endCursor;
    private List<InstaUser> followers;

    public FollowingFollower(String id) {
        this.id = id;
    }

    public FollowingFollower() {
    }
}
