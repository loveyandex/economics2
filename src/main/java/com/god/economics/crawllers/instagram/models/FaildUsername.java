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
public class FaildUsername {
    @Id
    private String id;
    private String username;
    private int howoften;

    public FaildUsername(String id, String username, int howoften) {
        this.id = id;
        this.username = username;
        this.howoften = howoften;
    }
}
