package com.god.economics.crawllers.instagram.login;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.models.direct.Inbox;
import com.github.instagram4j.instagram4j.requests.direct.DirectPendingInboxRequest;
import com.github.instagram4j.instagram4j.responses.direct.DirectInboxResponse;
import com.google.gson.Gson;

import static com.god.economics.crawllers.instagram.login.LoginWith.igClient;

public class PendingDircrts {

    public static void main(String[] args) throws Exception {
        IGClient igClient = igClient();

        DirectPendingInboxRequest directPendingInboxRequest = new DirectPendingInboxRequest();
        DirectInboxResponse directInboxResponse = igClient.sendRequest(directPendingInboxRequest).get();
        Inbox inbox = directInboxResponse.getInbox();

        inbox.getThreads().stream().forEach(igThread -> {
            igThread.getItems().forEach(threadItem -> {
                System.out.println(new Gson().toJson(igThread));
            });
        });


    }
}
