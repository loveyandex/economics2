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
        shortcode = "CJ1q1QhLLiH";
        shortcode = "CSG_FfELfUA";
        shortcode = "CazaN_aLUZw";
        String s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22CCd7hH5pdy_%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";
        s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";

        String cok="ig_cb=1; ig_did=13753609-3C4B-4F7C-90F8-BD83AD01F31D; mid=XysWIQALAAF87pJyPWzld3WgU42x; csrftoken=VOac5SxGQgZgUAwJ0E6qMwicUavO5JYA; sessionid=26898586489%3Aki41tkItQMmHgR%3A28; shbid=18485; shbts=1596659268.17796; ds_user_id=26898586489; rur=ASH; urlgen=\"{\\\"198.16.74.43\\\": 174}:1k3QGM:tOZafh3Uqv_uj99HXl-ZHmraSg4\"";
        cok = "ig_cb=2; ig_did=246836D0-1F7B-4C95-AD18-78A58EF7B588; mid=X8JgNQABAAGsDNtFcro-U2C4MdqM; csrftoken=1T73Zp5rrj9meoxEA0Nr1RpgGwoD1S7L; ds_user_id=30299824247; sessionid=30299824247%3AFT7dnTl5y0JQq2%3A7; shbid=9033; shbts=1607456246.2021677; rur=ASH; ig_direct_region_hint=FRC;";

        cok = "mid=YGx2LgALAAFzv6kYHQrS0iOfOWwE; ig_did=7E5ACD6D-EDAF-421B-BBD0-D60E5D428C15; shbid=13311; shbts=1624406764.4793892; rur=PRN; ds_user_id=38081432117; sessionid=38081432117%3AQM5aCtO0QrnViI%3A7; csrftoken=QnanSQhfCviS0KgwmFNlaZKYqD68gTWN";

        cok = "mid=YNRjBAALAAFgrrBwMRRmx9GD1lFs; ig_did=5DB39C23-A78A-4232-87ED-D8A48FF4613A; csrftoken=qzyM9xMlaIkfztbdNAQSexuqRaQ9VThy; ds_user_id=48365060212; sessionid=48365060212%3ArgmZPLiTJKIQnS%3A10; shbid=\"12891\\05448365060212\\0541661893053:01f7926503ba3df12d64078c42423317722ab42f5f92529c8965730e5b34b5778155f6a0\"; shbts=\"1630357053\\05448365060212\\0541661893053:01f7adc1e27a876b45859b46fe1b24bf8db81f0b86f1105375e8c292fda91154786e7657\"; rur=\"ASH\\05448365060212\\0541661959555:01f76da5c319cde704dc613dcf914f155e5b8018fb0e814dc8d35cd650ff4468951f2878\"";
        cok = "mid=YiQXKwALAAGdKGjreR2mwXduEETd; ig_did=84B42C62-D301-495A-ABA5-B8DBF93A2E3C; shbid=\"18799\\05446965647003\\0541678068404:01f74ebfdb3d350d833eb147a5268e02b9d28cc8004bc1d60f676d2377dc0d88b10c1e20\"; shbts=\"1646532404\\05446965647003\\0541678068404:01f759759fee21a6e0c2a9cce48e41692283381101c1fe289639f7967ae3bb5ba11795c1\"; csrftoken=p4BkbmZ8YiuyEt19Z48U9vv0Yt0QGQ74; ds_user_id=46965647003; sessionid=46965647003%3AhdFxDdkVYyxtgn%3A6; rur=\"FRC\\05446965647003\\0541678224423:01f782a30366f38c8cb1dc46f37a87145ebe25cba7a7be2ff42d25e4b5d64f58cfb64de9\"";

        Thread
                .sleep(10*1000);

        System.out.println(s);
        WithUrlLoggedCommnets.main(repository, shortcode, cok, s);

    }


    public  static void getConmmentsandUser(String shortcode,String coocki){

        String urlq = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";



    }

}