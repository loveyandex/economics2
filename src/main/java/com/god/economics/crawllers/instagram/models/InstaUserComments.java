package com.god.economics.crawllers.instagram.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * created By gOD on 1/26/2020 10:24 AM
 */
@Getter
@Setter
@Accessors(chain = true)
public class InstaUserComments {

    @Id
    private String id;
    private String userid;
    private String username;
    private List<String> comments = new ArrayList<>();

    public InstaUserComments addCommnet(String cmnt){
        comments.add(cmnt);
        return this;
    }

    public InstaUserComments() {
    }
}
