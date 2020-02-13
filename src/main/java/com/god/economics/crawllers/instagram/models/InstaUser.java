package com.god.economics.crawllers.instagram.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * created By gOD on 1/26/2020 10:24 AM
 */
@Getter
@Setter
@Accessors(chain = true)
public class InstaUser {

    @Id
    private String id;
    private String username;
    private String bio;
    private String externalUrl;
    private String fullName;
    private int edge_followed_by;
    private int edge_follow;
    private boolean isprivate;

    private String status;

    public InstaUser(String id, String username, String fullName,boolean isprivate) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.isprivate = isprivate;
    }

    public InstaUser(String id, String username, String bio, String externalUrl, String fullName, int edge_followed_by, int edge_follow) {
        this.id = id;
        this.username = username;
        this.bio = bio;
        this.externalUrl = externalUrl;
        this.fullName = fullName;
        this.edge_followed_by = edge_followed_by;
        this.edge_follow = edge_follow;
    }

    public InstaUser() {
    }
}
