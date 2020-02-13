package com.god.economics.crawllers.instagram.direct;

public class NewDirect {


    public static void main(String[] args) {
        String s = "--9b8cd5cb-766f-4689-ab68-14bf068944e2\n" +
                "Content-Disposition: form-data; name=\"recipient_users\"\n" +
                "\n" +
                "[[\"7475418881\"]]\n" +
                "--9b8cd5cb-766f-4689-ab68-14bf068944e2\n" +
                "Content-Disposition: form-data; name=\"client_context\"\n" +
                "\n" +
                "9b8cd5cb-766f-4689-ab68-14bf068944e2\n" +
                "--9b8cd5cb-766f-4689-ab68-14bf068944e2\n" +
                "Content-Disposition: form-data; name=\"thread\"\n" +
                "\n" +
                "[\"0\"]\n" +
                "--9b8cd5cb-766f-4689-ab68-14bf068944e2\n" +
                "Content-Disposition: form-data; name=\"text\"\n" +
                "\n" +
                "b'salam'\n" +
                "--9b8cd5cb-766f-4689-ab68-14bf068944e2--\n" ;

        String ss = "{'User-Agent': 'Instagram 10.26.0 Android (18/4.3; 320dpi; 720x1280; Xiaomi; HM 1SW; armani; qcom; en_US)', 'Accept-Encoding': 'gzip, deflate', 'Accept': '*/*', 'Connection': 'keep-alive', 'Content-Type': 'multipart/form-data; boundary=9b8cd5cb-766f-4689-ab68-14bf068944e2', 'Cookie2': '$Version=1', 'Accept-Language': 'en-en', 'Proxy-Connection': 'keep-alive'}";
        


    }

}
