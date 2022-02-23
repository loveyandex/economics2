package com.god.economics.crawllers.instagram;

/**
 * created By gOD on 7/31/2020 9:56 AM
 */

public class LoginConfig {
    public final static String cookie = "mid=YeiDzgALAAHt4ZgjMJLbka5zvUrU; ig_did=A3FF0530-90AD-451E-B2D0-3B7E9B00D973; ig_nrcb=1; csrftoken=fjWWNFORQrAcZvkWVUu0QLpT11u5vvBv; ds_user_id=48586000684; sessionid=48586000684%3ALUgXxuWN8cmtJW%3A6; shbid=\"550\\05448586000684\\0541674758828:01f7f592c3374b9304d06eaee4a0491b2b0730d03efe991b34550318fef48357b426dc49\"; shbts=\"1643222828\\05448586000684\\0541674758828:01f746b308a364cdac055278bfc04297066409425d0bb6298ea5a89dfbcab42e2b05b761\"; rur=\"LDC\\05448586000684\\0541674871478:01f721d0d59337533fd80db89f3e73de5993e1a5944c9874d33b022b967bef3923cab6f5\"";


    public static final String IGCLAIM = "hmac.AR2cugIZgXDMS7OrwO9szfy3SNejWHppB1sM8E8aoTPOrHdw";
    public final static String csrftoken = "h982xyRUT5CeQwglfLVFy4ArPJzvxNra";
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
