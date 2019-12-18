package com.god.economics.crawllers.bama.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * created By gOD on 12/16/2019 12:18 AM
 */
@Getter
@Setter
@Accessors(chain = true)

public class Brand {
    @Id
    private String id;
    private String name;

}
