package com.god.economics.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * created By gOD on 6/19/2020 6:50 PM
 */
@Component
public class Comp {

    public Comp() {
        System.out.println("king amin");
    }

    @Bean()
    @Qualifier("public")
    public String publicInstance() {
        return new String("publicInstance");
    }

    public void doWork() {
        // Component method implementation omitted
    }
}
