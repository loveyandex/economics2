package com.god.economics.crawllers.instagram.api.hashtags;

import com.god.economics.crawllers.instagram.all.IdProccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * created By gOD on 6/26/2020 8:44 PM
 */

public class run {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("./ids_new.txt"));

        FileWriter writer = new FileWriter("username_new" + ".txt", true);
        int ip=0;
        while (scanner.hasNextLine()) {
            String id = scanner.nextLine();
            ip++;
            if (ip<1397)
                continue;

            try {
                String s = IdProccess.IDtoUsername(id);
                try {
                    writer.write(s + "\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.close();
        scanner.close();
    }
}
