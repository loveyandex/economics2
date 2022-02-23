package com.god.economics.crawllers.instagram.login;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.models.direct.IGThread;
import com.github.instagram4j.instagram4j.requests.direct.DirectInboxRequest;
import com.github.instagram4j.instagram4j.requests.direct.DirectThreadsRequest;
import com.github.instagram4j.instagram4j.responses.direct.DirectInboxResponse;
import com.github.instagram4j.instagram4j.responses.direct.DirectThreadsResponse;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import static com.god.economics.crawllers.instagram.login.LoginWith.igClient;

@SpringBootApplication
public class LoginApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            IGClient client = igClient();

            DirectInboxRequest inbox = new DirectInboxRequest();
            CompletableFuture<DirectInboxResponse> directInboxResponseCompletableFuture = client.sendRequest(inbox);

            DirectInboxResponse directInboxResponse = directInboxResponseCompletableFuture.get();



            AtomicInteger counter = new AtomicInteger();
            for (IGThread thread : directInboxResponse.getInbox().getThreads()) {

                thread.getItems().forEach(instagramInboxThreadItem -> {

                    System.out.println(new Gson().toJson(instagramInboxThreadItem));


                });
            }

            String oldest_cursor = directInboxResponse.getInbox().getOldest_cursor();

            DirectInboxRequest inboxRequest = new DirectInboxRequest().
                    cursor(oldest_cursor)
                    .persistent_badging(true);


            CompletableFuture<DirectInboxResponse> directInboxResponseCompletableFuture1 = client.sendRequest(inboxRequest);
            DirectInboxResponse directInboxResponse1 = directInboxResponseCompletableFuture1.get();



            for (IGThread thread : directInboxResponse1.getInbox().getThreads()) {

                DirectThreadsRequest directThreadsRequest = new DirectThreadsRequest(thread.getThread_id(), thread.getOldest_cursor());
                DirectThreadsResponse directThreadsResponse = client.sendRequest(directThreadsRequest).get();

                System.err.println(new Gson().toJson(directThreadsResponse));


                thread.getItems().forEach(instagramInboxThreadItem -> {

                    System.err.println(new Gson().toJson(instagramInboxThreadItem));
                    System.err.println("new Gson().toJson(instagramInboxThreadItem)");


                });
            }




            System.out.println(new Gson().toJson(client));

        } catch (IGLoginException e) {
            e.printStackTrace();
        }

    }

}