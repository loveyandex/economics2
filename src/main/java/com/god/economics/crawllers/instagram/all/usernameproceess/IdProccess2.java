package com.god.economics.crawllers.instagram.all.usernameproceess;

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

public class IdProccess2 {


    public static void main(String[] args) throws IOException {
        HashSet<String> usernames = new HashSet<>();
        Scanner usernamescanner = new Scanner(new File("usernames.txt"));
        int ap = 0;
        while (usernamescanner.hasNextLine()) {
            String username = usernamescanner.nextLine();
            usernames.add(username);
        }
        usernamescanner.close();

        HashSet<String> userids = new HashSet<>();
        Scanner useridScanner = new Scanner(new File("ids.txt"));

        while (useridScanner.hasNextLine()) {
            String id = useridScanner.nextLine();
            userids.add(id);
        }
        useridScanner.close();


        String tagid = "1";
        FileWriter writer = new FileWriter("usernames" + tagid + ".txt", true);
        Scanner scanner = new Scanner(new File("idtemp.txt"));

        while (scanner.hasNextLine()) {
            String id = scanner.nextLine();

            if (userids.contains(id)) {
                System.out.println("id before investigated");

            } else {

                String resp = null;
                try {
                    System.out.println("id: "+id);
                    resp = Reqs.getReq("https://www.instagram.com/graphql/query/?query_hash=c9100bf9110dd6361671f113dd02e7d6&variables=%7B%22user_id%22%3A%22" + id + "%22%2C%22include_chaining%22%3Atrue%2C%22include_reel%22%3Atrue%2C%22include_suggested_users%22%3Afalse%2C%22include_logged_out_extras%22%3Afalse%2C%22include_highlight_reels%22%3Atrue%2C%22include_related_profiles%22%3Afalse%7D");
                    JSONObject jsonObject = new JSONObject(resp);
                    String username = (String) ((JSONObject) ((JSONObject) ((JSONObject) ((JSONObject) jsonObject.get("data")).get("user")).get("reel"))
                            .get("user")).get("username");
                    if (usernames.contains(username))
                        System.out.println(username);
                    writer.write(username + "\r\n");
                    writer.flush();
                    Thread.sleep(800);
                } catch (Exception e) {
                    System.out.println(resp);
                    e.printStackTrace();
                }

            }
        }


    }
}
