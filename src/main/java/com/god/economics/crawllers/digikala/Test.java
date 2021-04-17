package com.god.economics.crawllers.digikala;

public class Test {


    public static void main(String[] args) {
        String d = "مودم 4G قابل حمل جی یو مدل M2S\n";

        String[] split = d.split("[0-9][a-zA-Z]");

        for (String s : split) {
            System.out.println(s);
        }
    }
}
