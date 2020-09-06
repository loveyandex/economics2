package com.god.economics.crawllers.instagram.clone;

import com.god.economics.crawllers.Reqs;
import org.json.JSONObject;

import java.io.IOException;

/**
 * created By gOD on 7/7/2020 12:28 AM
 */

public class ClonePage {
    private String pageId;
    private String pageUserName;


    public static class Builder {
        private String pageId;
        private String pageUserName;

        public ClonePage build() {
            return new ClonePage(this);
        }

        public Builder pageUserName(String pageUserName) {
            this.pageUserName = pageUserName;
            return this;
        }

        public Builder pageId(String pageId) {
            this.pageId = pageId;
            return this;
        }
    }


    public ClonePage(Builder builder) {
        this.pageId = builder.pageId;
        this.pageUserName = builder.pageUserName;

    }

    public void _clone(    ) throws IOException {

        String url = "https://www.instagram.com/" + this.pageUserName + "/?__a=1";
        String resp = Reqs.getReq(url);

        JSONObject jsonObject = new JSONObject(resp);

        Object graphql = jsonObject.get("graphql");


        Object bio0 = (((JSONObject) (((JSONObject) graphql).get("user")))
                .get("biography"));


        String bio = bio0.toString();

        Object o = ((JSONObject) (((JSONObject) graphql).get("user"))).get("external_url");
        String externalurl = o.toString();

        String full_name = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                .get("full_name");
        String userId = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                .get("id");

        int edge_followed_by = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                .get("edge_followed_by")).get("count"));

        int edge_follow = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                .get("edge_follow")).get("count"));

        System.out.println();



    }


    public static void main(String[] args) {
        ClonePage donyaPage = new ClonePage.Builder().pageUserName("adidas.tandis").build();
        try {
            donyaPage._clone();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
