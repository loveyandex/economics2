package com.god.economics.crawllers.instagram.donyacomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class withnexttokenApplication implements CommandLineRunner {

    @Autowired
    private UserCommentedRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(withnexttokenApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String shortcode = "CDjekw2jd6o";
//        shortcode = "CCoYNY8HcQS";
//        shortcode = "CCm-CVjlozN";
//        shortcode = "CDQFo-On_To";

     shortcode = "CDjekw2jd6o";
        shortcode = "CDl79cpjRKH";
        shortcode = "CDoKOPajDKF";
        shortcode = "CDqaDIJDXUa";
        shortcode = "CDrICqUjeA_";
        String s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22CCd7hH5pdy_%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";
        s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";

        String cok="ig_cb=1; ig_did=13753609-3C4B-4F7C-90F8-BD83AD01F31D; mid=XysWIQALAAF87pJyPWzld3WgU42x; csrftoken=VOac5SxGQgZgUAwJ0E6qMwicUavO5JYA; sessionid=26898586489%3Aki41tkItQMmHgR%3A28; shbid=18485; shbts=1596659268.17796; ds_user_id=26898586489; rur=ASH; urlgen=\"{\\\"198.16.74.43\\\": 174}:1k3QGM:tOZafh3Uqv_uj99HXl-ZHmraSg4\"";
        cok = "csrftoken=92rh9ugksst6s6P5wDDAAkjJdkIibV9j; ig_did=A30349A2-397A-49BC-BE9C-EA6994B5C2A8; mid=XzVkgAALAAGMsNEmH78Qob7DdcMS; sessionid=30299824247%3ATBS0U5dX8M8fgk%3A17; shbid=9033; shbts=1597334658.1844387; rur=ASH";



        System.out.println(s);
        WithUrlLoggedCommnets.main(repository, shortcode, cok, s);

    }

}