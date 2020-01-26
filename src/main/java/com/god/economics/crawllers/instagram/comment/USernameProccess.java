package com.god.economics.crawllers.instagram.comment;

import com.god.economics.PinstaUserRepo;
import com.god.economics.crawllers.Reqs;
import com.god.economics.crawllers.instagram.comment.models.PinstaUser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * created By gOD on 1/26/2020 8:59 AM
 */
@RestController
public class USernameProccess {

    @Autowired
    private PinstaUserRepo pinstaUserRepo;

    @GetMapping("/bio")
    public void main() throws IOException {

        Scanner scanner = new Scanner(new File("usernames.txt"));
        int ap = 0;
        while (scanner.hasNext()) {

            String resp = null;
            try {
                String username = scanner.nextLine();
                String url = "https://www.instagram.com/" + username + "/?__a=1";
                resp = Reqs.getReq(url);

                JSONObject jsonObject = new JSONObject(resp);

                Object graphql = jsonObject.get("graphql");


                String bio = ((String) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("biography"));

                String[] split = bio.split("۰۹");
                String externalurl = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("external_url");

                String full_name = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("full_name");

                int edge_followed_by = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("edge_followed_by")).get("count"));
                int edge_follow = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("edge_follow")).get("count"));
                PinstaUser pinstaUser = new PinstaUser(username, bio, externalurl, full_name, edge_followed_by, edge_follow);

                pinstaUserRepo.save(pinstaUser);

                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(resp);
                e.printStackTrace();
            }

        }


    }
}
