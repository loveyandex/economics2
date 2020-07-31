//package com.god.economics.crawllers.instagram.donyacomment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class withnexttokenApplication implements CommandLineRunner {
//
//    @Autowired
//    private UserCommentedRepository repository;
//
//    public static void main(String[] args) {
//        SpringApplication.run(withnexttokenApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String shortcode = "CCd7hH5pdy_";
////        shortcode = "CCoYNY8HcQS";
////        shortcode = "CCm-CVjlozN";
////        shortcode = "CDQFo-On_To";
//        String s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22CCd7hH5pdy_%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";
//        s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";
//
//        String cok="ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; shbid=9033; shbts=1595859409.097483; rur=ASH; csrftoken=gjIbkNgCQ7klAZKOpBQTmwZjzHi3IAM5; ds_user_id=30299824247; sessionid=30299824247%3AZm6CiDhNYe8wco%3A8; urlgen=\"{\\\"89.34.253.72\\\": 58224\\054 \\\"37.148.94.209\\\": 58224}:1k0urK:E8QAEZaB9aWBVb_tYe9sCNJcDRg\"";
//
//
//        System.out.println(s);
//        WithUrlLoggedCommnets.main(repository, shortcode, cok, s);
//
//    }
//
//}