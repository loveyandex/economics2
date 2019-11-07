package com.god.economics.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * created By gOD on 11/2/2019 2:36 AM
 */
@Getter
@Setter
@Accessors(chain = true)

public class GenAuthor {

    @Id
    private String id;
    private String name;
//    private String link;
}
