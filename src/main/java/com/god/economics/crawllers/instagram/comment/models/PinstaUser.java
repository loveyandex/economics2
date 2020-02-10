package com.god.economics.crawllers.instagram.comment.models;

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
public class PinstaUser {
    @Id
    private String id;
    private String username;
    private String bio;
    private String externalUrl;
    private String fullName;
    private String probablyCity;
    private int edge_followed_by;
    private int edge_follow;

    public PinstaUser(String id, String username, String bio, String externalUrl, String fullName, String probablyCity, int edge_followed_by, int edge_follow) {
        this.id = id;
        this.username = username;
        this.bio = bio;
        this.externalUrl = externalUrl;
        this.fullName = fullName;
        this.probablyCity = probablyCity;
        this.edge_followed_by = edge_followed_by;
        this.edge_follow = edge_follow;
    }

    public PinstaUser(String id, String username, String bio, String externalUrl, String fullName, int edge_followed_by, int edge_follow) {
        this.id = id;
        this.username = username;
        this.bio = bio;
        this.externalUrl = externalUrl;
        this.fullName = fullName;
        this.edge_followed_by = edge_followed_by;
        this.edge_follow = edge_follow;
    }
}
