package com.god.economics.crawllers.jvd;

/**
 * created By gOD on 12/6/2020 4:14 PM
 */

public class Response {
    private String title;
    private String backlink;
    private String cat;

    public Response(String title, String backlink, String cat) {
        this.title = title;
        this.backlink = backlink;
        this.cat = cat;
    }
}
