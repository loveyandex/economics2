package com.god.economics.joobin.functional;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * created By gOD on 6/17/2020 11:43 AM
 */

public class FF {
    public static void main(String[] args) {

        Stream.iterate(0,integer -> integer+1)
                .parallel()
                .limit(1_000_000)
                .forEach(integer -> System.out.println(integer));




        IntStream intStream = IntStream.rangeClosed(0, 21)
                .sequential();

        intStream.parallel().forEach(System.out::println);


        List<Integer> dd = Arrays.asList(2, 3, 4, 5, 6);

        Iterator<Integer> iterator = dd.iterator();

        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }


        List<Integer> number = Arrays.asList(2, 3, 4, 5, 6);
        number.parallelStream().filter(integer -> integer > 0);

        int even = number.stream()
                .filter(x -> x % 2 == 0)
                .reduce(0, (ans, i) -> ans * i);
        List<String> strings = Arrays.asList("2", "3", "4", "5", "6");

        String evesn = strings.stream()
                .filter(s -> s.length() == 1)
                .reduce("0", (ans, i) -> ans + i);
        System.out.println(evesn);

        List<Integer> number2 = Arrays.asList(2, 3, 4, 5, 3);

        List<Integer> collect = number2.stream().distinct().collect(Collectors.toList());

        Stream.Builder<Integer> builder = Stream.builder();
        Stream<Integer> build = builder
                .add(23)
                .add(23)
                .add(23)
                .build();


        Stream<Integer> integerStream = Stream.of(1, 23, 42, 323, 231, 2, 12, 12, 232);
        for (int i = 0; i < 32; i++) {
            integerStream.findAny().ifPresent(System.out::println);
        }

        System.out.println();

    }
}
