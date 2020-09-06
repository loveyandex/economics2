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
        shortcode = "CEtl53jjWHU";
        String s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22CCd7hH5pdy_%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";
        s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";

        String cok="ig_cb=1; ig_did=13753609-3C4B-4F7C-90F8-BD83AD01F31D; mid=XysWIQALAAF87pJyPWzld3WgU42x; csrftoken=VOac5SxGQgZgUAwJ0E6qMwicUavO5JYA; sessionid=26898586489%3Aki41tkItQMmHgR%3A28; shbid=18485; shbts=1596659268.17796; ds_user_id=26898586489; rur=ASH; urlgen=\"{\\\"198.16.74.43\\\": 174}:1k3QGM:tOZafh3Uqv_uj99HXl-ZHmraSg4\"";
        cok = "ig_cb=1; ig_did=E3DBBCB8-A468-40BC-920A-A9EC445D8710; mid=XqXF2gALAAE2ZeIZ2Nx4Q5DKTjy5; csrftoken=8ZMcmXkg75HwURWSsEMxaDK5zBRqiQ5n; ds_user_id=38081432117; sessionid=38081432117%3AFTMnTanLgiHvwI%3A25; shbid=\"13311\\05438081432117\\0541630605107:01f7287699b1953edf648a184cf1f79accdf6caf77b3f8ba13de03a5e817ae26f490a6d1\"; shbts=\"1599069107\\05438081432117\\0541630605107:01f7ce7b62392b8404a6d2f4f7bf2d7de03d98473011bbedf26c16b28dab7c277053f334\"; ig_direct_region_hint=\"FRC\\05438081432117\\0541630733642:01f728e3d4c672834e64857bf9e12e64fe1d3075762465388294c870fe47fe358a8cc98a\"; rur=\"ATN\\05438081432117\\0541630750062:01f7ae2b7a4dc7c610834a1bf98c25fbd66af4ffcd303dd671adfca39b8c5014d991608d\"";



        System.out.println(s);
        WithUrlLoggedCommnets.main(repository, shortcode, cok, s);

    }

}