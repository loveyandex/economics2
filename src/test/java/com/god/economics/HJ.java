package com.god.economics;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * created By gOD on 11/20/2020 2:11 PM
 */

public class HJ {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (true) {
            new Thread(() -> atomicInteger.incrementAndGet()).start();
            new Thread(() -> atomicInteger.incrementAndGet()).start();

            Thread.sleep(100);

            System.out.println(atomicInteger);
            atomicInteger.set(0);


        }


    }
}
