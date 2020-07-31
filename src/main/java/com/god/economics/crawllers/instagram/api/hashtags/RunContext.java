package com.god.economics.crawllers.instagram.api.hashtags;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * created By gOD on 6/26/2020 8:51 PM
 */

public class RunContext {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(
                "com.god.economics.crawllers.instagram.api.hashtags");
        GreetingService bean = context.getBean(GreetingService.class);
//        DataMongo bean1 = context.getBean(DataMongo.class);
//        List<String> all = bean1.findAll();
//        bean.greet();

    }
}
