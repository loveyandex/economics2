package com.god.economics.crawllers.instagram;

/**
 * created By gOD on 7/31/2020 9:56 AM
 */

public class LoginConfig {
    public final static String cookie = "ig_cb=1; ig_did=D80D9A22-AE7C-4738-AE68-2170E3B7ABCB; mid=X3VrVAALAAGbmHg-JBNj1CCd4116; csrftoken=jYhVUQB2JqJfoTM5konL1rnurIJ96ruI; ds_user_id=38081432117; shbid=13311; sessionid=38081432117%3AeGyENEyyGDJ7Z9%3A19; shbts=1607738452.463118; rur=FRC; urlgen=\"{\\\"5.236.146.194\\\": 58224\\054 \\\"151.236.18.7\\\": 20836}:1knuTO:8_GPQfC0EuCzixVkaxvM2m_JER4\"";
    public static final String IGCLAIM = "hmac.AR2cugIZgXDMS7OrwO9szfy3SNejWHppB1sM8E8aoTPOrHdw";
    public final static String csrftoken = "jYhVUQB2JqJfoTM5konL1rnurIJ96ruI";
    public static String igappid = "936619743392459";


    public static class CookieParamsForSendingFollow{
        public final static String mid = "mid";
//        public final static String ds_user_id = "ds_user_id";
        public final static String sessionid = "sessionid";
        public final static String ig_did = "ig_did";
        public final static String csrftoken = "csrftoken";
        public final static String shbid = "shbid";
        public final static String shbts = "shbts";
        public final static String rur = "rur";
        public final static String[] all = {mid, sessionid, ig_did, csrftoken, shbid, shbts, rur};
//        public final static String urlgen = "urlgen";

    }

}
