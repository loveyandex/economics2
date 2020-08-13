package com.god.economics.crawllers.instagram;

/**
 * created By gOD on 7/31/2020 9:56 AM
 */

public class LoginConfig {
    public final static String cookie = "ig_cb=1; mid=XKH3nQALAAHhnv9Y7d2jG33GUBXa; ig_did=B4A9D94B-4507-4326-BDA7-6FAF7D9398D0; datr=fZAjXhUwOXatDeFYjwO3kBAj; shbid=9033; csrftoken=gjIbkNgCQ7klAZKOpBQTmwZjzHi3IAM5; ds_user_id=30299824247; sessionid=30299824247%3AZm6CiDhNYe8wco%3A8; shbts=1596127512.6155002; rur=ASH; urlgen=\"{\\\"5.236.145.154\\\": 58224\\054 \\\"5.239.198.220\\\": 58224\\054 \\\"37.235.48.166\\\": 51290\\054 \\\"2.182.208.43\\\": 58224}:1k1NvZ:vFoWvNrxD88iOWThfI6HJhb2jx0\"";
    public static String csrftoken = "";

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
