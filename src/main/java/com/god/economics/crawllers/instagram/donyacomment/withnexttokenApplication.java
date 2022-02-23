package com.god.economics.crawllers.instagram.donyacomment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
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
        shortcode = "CYougfBt6HW";
        String s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22CCd7hH5pdy_%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";
        s = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";

        String cok="ig_cb=1; ig_did=13753609-3C4B-4F7C-90F8-BD83AD01F31D; mid=XysWIQALAAF87pJyPWzld3WgU42x; csrftoken=VOac5SxGQgZgUAwJ0E6qMwicUavO5JYA; sessionid=26898586489%3Aki41tkItQMmHgR%3A28; shbid=18485; shbts=1596659268.17796; ds_user_id=26898586489; rur=ASH; urlgen=\"{\\\"198.16.74.43\\\": 174}:1k3QGM:tOZafh3Uqv_uj99HXl-ZHmraSg4\"";
        cok = "ig_cb=2; ig_did=246836D0-1F7B-4C95-AD18-78A58EF7B588; mid=X8JgNQABAAGsDNtFcro-U2C4MdqM; csrftoken=1T73Zp5rrj9meoxEA0Nr1RpgGwoD1S7L; ds_user_id=30299824247; sessionid=30299824247%3AFT7dnTl5y0JQq2%3A7; shbid=9033; shbts=1607456246.2021677; rur=ASH; ig_direct_region_hint=FRC;";

        cok = "mid=YGx2LgALAAFzv6kYHQrS0iOfOWwE; ig_did=7E5ACD6D-EDAF-421B-BBD0-D60E5D428C15; shbid=13311; shbts=1624406764.4793892; rur=PRN; ds_user_id=38081432117; sessionid=38081432117%3AQM5aCtO0QrnViI%3A7; csrftoken=QnanSQhfCviS0KgwmFNlaZKYqD68gTWN";

        cok = "mid=YNRjBAALAAFgrrBwMRRmx9GD1lFs; ig_did=5DB39C23-A78A-4232-87ED-D8A48FF4613A; csrftoken=qzyM9xMlaIkfztbdNAQSexuqRaQ9VThy; ds_user_id=48365060212; sessionid=48365060212%3ArgmZPLiTJKIQnS%3A10; shbid=\"12891\\05448365060212\\0541661893053:01f7926503ba3df12d64078c42423317722ab42f5f92529c8965730e5b34b5778155f6a0\"; shbts=\"1630357053\\05448365060212\\0541661893053:01f7adc1e27a876b45859b46fe1b24bf8db81f0b86f1105375e8c292fda91154786e7657\"; rur=\"ASH\\05448365060212\\0541661959555:01f76da5c319cde704dc613dcf914f155e5b8018fb0e814dc8d35cd650ff4468951f2878\"";
        cok = "mid=YYrdhgALAAEYiXPu0PW6m2YdYCKQ; ig_did=708EBF26-AFEE-4E07-BCB4-07E6C33C6979; csrftoken=IDl7KmC5Bg2tGJji0QvMYwVNwkTNROCt; ds_user_id=38081432117; sessionid=38081432117%3AMItMyvUQgRzJSl%3A27; shbid=\"2510\\05438081432117\\0541673551394:01f77485ff1ce56d575923891e5eda811d17219daf0b65414bc72a31b1bc399eee5d27e3\"; shbts=\"1642015394\\05438081432117\\0541673551394:01f772cd5bdc8a5b766bab05dda37f727bc8f4f20f3d8c9d0b8e959321bd51b7b1e02f1d\"; rur=\"RVA\\05438081432117\\0541673619478:01f74933844bc71dd5b679f47ba2497594f7300fc5894712b6cd920e0db84e18faea3e40\"";




        System.out.println(s);
        WithUrlLoggedCommnets.main(repository, shortcode, cok, s);

    }


    public  static void getConmmentsandUser(String shortcode,String coocki){

        String urlq = "https://www.instagram.com/graphql/query/?query_hash=bc3296d1ce80a24b1b6e40b1e72903f5&variables=%7B%22shortcode%22%3A%22"+shortcode+"%22%2C%22first%22%3A120%2C%22after%22%3A%22%7B%5C%22bifilter_token%5C%22%3A+%5C%22KDMBAgC4AP______________________________AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA%5C%22%7D%22%7D";



    }

}