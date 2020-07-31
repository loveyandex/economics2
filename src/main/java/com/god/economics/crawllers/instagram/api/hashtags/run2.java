package com.god.economics.crawllers.instagram.api.hashtags;

import com.god.economics.InstaUserIdRepo;
import com.god.economics.crawllers.instagram.all.IdProccess;
import com.god.economics.crawllers.instagram.models.InstaUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * created By gOD on 6/26/2020 8:44 PM
 */

@RestController
public class run2 {
    @Autowired
    private InstaUserIdRepo instaUserIdRepo;

    @GetMapping("/addids")
    public void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("./ids_new.txt"));

        int ip = 0;
        while (scanner.hasNextLine()) {
            String id = scanner.nextLine();
            instaUserIdRepo.save(new InstaUserId().setUserId(id));
        }

        scanner.close();
    }
}
