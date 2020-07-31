package com.god.economics.joobin.functional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * created By gOD on 6/17/2020 5:32 PM
 */

public class Futures {
    public static void main(String[] args) {
        //we have 10 threads pool to execute tasks
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> submit = executorService.submit(() -> {
            System.out.println("in call run");
            Thread.sleep(5000);
            return Thread.currentThread().getName();
        });
        //            String s = submit.get();
//            System.out.println(s);
        executorService.shutdown();
    }
}
