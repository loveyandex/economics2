package com.god.economics.crawllers.instagram.api.story;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * created By gOD on 12/15/2020 5:54 AM
 */

public class cf {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4222);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "god is my energy";
        }).thenApplyAsync(u -> {
            System.out.println(u);
            return ("");
        }).exceptionally(throwable ->
        {
            System.out.println(throwable.getCause());
            return "ddd";
        }).get();

        System.out.println("out");

//        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
//
//        try {
//            completableFuture.complete("ddd");
//            System.out.println(completableFuture.get());
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//

    }
}
