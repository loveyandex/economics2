package com.god.economics.crawllers.instagram.comment.usernameproceess;

import com.god.economics.crawllers.Reqs;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * created By gOD on 1/26/2020 8:59 AM
 */

public class UsernameFinder {


    public static void main(String[] args) throws IOException {


        String id = "";

        String resp = Reqs.getReq("https://www.instagram.com/graphql/query/?query_hash=c9100bf9110dd6361671f113dd02e7d6&variables=%7B%22user_id%22%3A%22" + id + "%22%2C%22include_chaining%22%3Atrue%2C%22include_reel%22%3Atrue%2C%22include_suggested_users%22%3Afalse%2C%22include_logged_out_extras%22%3Afalse%2C%22include_highlight_reels%22%3Atrue%2C%22include_related_profiles%22%3Afalse%7D");
        JSONObject jsonObject = new JSONObject(resp);
        String username = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("data")).get("user")).get("reel"))
                .get("user")).get("username");


    }
}
