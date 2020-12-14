package com.god.economics.crawllers.instagram;

/**
 * created By gOD on 7/31/2020 9:56 AM
 */

public class LoginConfig {
    public final static String cookie = "ig_cb=2; ig_did=246836D0-1F7B-4C95-AD18-78A58EF7B588; mid=X8JgNQABAAGsDNtFcro-U2C4MdqM; ds_user_id=30299824247; shbid=9033; ig_direct_region_hint=FRC; shbts=1607738432.4921987; csrftoken=3PNVY7vtxfRijHKuGbdaRigxZAi9ktw2; sessionid=30299824247%3A7Z0Wq5tWhGLF79%3A19; rur=FRC";
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
