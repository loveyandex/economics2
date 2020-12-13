package com.god.economics.crawllers.instagram.login;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoginApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            IGClient client = IGClient.builder()
                    .username("hhhh")
                    .password("paksilen_market1993")
                    .login();

            System.out.println(new Gson().toJson(client));

        } catch (IGLoginException e) {
            e.printStackTrace();
        }

    }

}