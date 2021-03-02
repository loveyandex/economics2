package com.god.economics.crawllers.instagram;

/**
 * created By gOD on 7/31/2020 9:56 AM
 */

public class LoginConfig {
    public final static String cookie = "ig_did=A726D8F8-CADC-4764-AD82-E9E120FA7F8F; mid=X9aC-AALAAGhYsEF1SXn51feWnPU; ig_nrcb=1; shbid=13311; shbts=1607895675.4561968; ds_user_id=45143507336; rur=FRC; csrftoken=h982xyRUT5CeQwglfLVFy4ArPJzvxNra; sessionid=45143507336%3ACTs3joPTiryfFg%3A3;";


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
