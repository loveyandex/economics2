package com.god.economics.joobin.functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created By gOD on 6/17/2020 9:24 AM
 */

public class Predictt {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("king", "queen", "god", "king");
        Stream<String> stringStream = strings
                .stream()
                .filter(s -> s.intern() == s)
                .map(s -> s.intern() + "god");

        List<String> collect = stringStream.collect(Collectors.toList());

        System.out.println();
    }
}
