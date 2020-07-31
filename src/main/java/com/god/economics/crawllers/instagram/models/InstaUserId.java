package com.god.economics.crawllers.instagram.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * created By gOD on 6/27/2020 9:14 PM
 */

@Getter
@Setter
@Accessors(chain = true)
public class InstaUserId {
    @Id
    private String id;
    private String userId;

}
