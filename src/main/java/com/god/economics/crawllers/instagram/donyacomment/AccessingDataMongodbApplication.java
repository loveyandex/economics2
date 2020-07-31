//package com.god.economics.crawllers.instagram.donyacomment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class AccessingDataMongodbApplication implements CommandLineRunner {
//
//    @Autowired
//    private UserCommentedRepository repository;
//
//    public static void main(String[] args) {
//        SpringApplication.run(AccessingDataMongodbApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String shortcode = "CCd7hH5pdy_";
//        String cookie="ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; shbid=9033; ds_user_id=30299824247; sessionid=30299824247%3AYOHlYpbobFAEUO%3A0; csrftoken=IcmImH7c4dqZkDI7NaEN482jpnjE8dRO; shbts=1595243079.833013; rur=ASH; urlgen=\"{\\\"2.147.185.83\\\": 44244}:1jxUd3:K_HjuSW_0bzot5K8m5UJqQhXylA\"";
//        LoggedCommnets.main(repository, shortcode, args, cookie);
//
//    }
//
//
//}