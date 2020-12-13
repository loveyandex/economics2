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
        shortcode = "CD6OeIzDdiD";
        shortcode = "CD_QXB5DwDg";
        shortcode = "CEja_RcjPB6";
        shortcode = "CFCt2tbjguM";
        shortcode = "CFCt2tbjguM";
        shortcode = "CFWyP72j_0V";
        shortcode = "CF7R5lfDXYD";
        shortcode = "CF7rbu4DQ2u";
        shortcode = "CGA2iBODvTj";
        shortcode = "CGPvwRJDfNT";
        shortcode = "CGpNo26l90K";
        shortcode = "CGsg12eLLy4";
        shortcode = "CHYZ00brgKu";
        shortcode = "CITdFVOlAXq";
        shortcode = "CILZVXILPgQ";
        String s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22CCd7hH5pdy_%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";
        s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";

        String cok="ig_cb=1; ig_did=13753609-3C4B-4F7C-90F8-BD83AD01F31D; mid=XysWIQALAAF87pJyPWzld3WgU42x; csrftoken=VOac5SxGQgZgUAwJ0E6qMwicUavO5JYA; sessionid=26898586489%3Aki41tkItQMmHgR%3A28; shbid=18485; shbts=1596659268.17796; ds_user_id=26898586489; rur=ASH; urlgen=\"{\\\"198.16.74.43\\\": 174}:1k3QGM:tOZafh3Uqv_uj99HXl-ZHmraSg4\"";
        cok = "ig_cb=2; ig_did=246836D0-1F7B-4C95-AD18-78A58EF7B588; mid=X8JgNQABAAGsDNtFcro-U2C4MdqM; csrftoken=1T73Zp5rrj9meoxEA0Nr1RpgGwoD1S7L; ds_user_id=30299824247; sessionid=30299824247%3AFT7dnTl5y0JQq2%3A7; shbid=9033; shbts=1607456246.2021677; rur=ASH; ig_direct_region_hint=FRC;";



        System.out.println(s);
        WithUrlLoggedCommnets.main(repository, shortcode, cok, s);

    }

}