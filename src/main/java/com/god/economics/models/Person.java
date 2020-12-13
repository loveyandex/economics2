package com.god.economics.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * created By gOD on 11/2/2020 5:35 PM
 */


@Getter
@Setter
@Accessors(chain = true)
public class Person {

    @Id
    private String id;
    private String lastname;
    private String firstname;
    private String email;

    public Person(String lastname, String firstname, String email, Integer age) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.age = age;
    }

    private Integer age;

}
