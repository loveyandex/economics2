package com.god.economics.crawllers.instagram.clone;

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


    public static void main(String[] args) {
        ClonePage donyaPage = new ClonePage.Builder().pageId("121212").pageUserName("donya").build();

    }
}
