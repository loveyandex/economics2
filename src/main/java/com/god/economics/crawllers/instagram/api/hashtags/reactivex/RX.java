package com.god.economics.crawllers.instagram.api.hashtags.reactivex;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

import java.util.stream.Stream;

/**
 * created By gOD on 6/27/2020 9:29 AM
 */

public class RX {
    public static void main(String[] args) {
        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0)
                ;

        Stream.builder().add(25555).add(43).add(434).add(4343).build().findFirst().ifPresent(System.out::println);

        @NonNull Single<Boolean> all = flow.all(integer ->integer>0);
        all.blockingSubscribe(aBoolean -> System.out.println(aBoolean));


    }
}
