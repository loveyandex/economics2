package com.god.economics.joobin.functional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * created By gOD on 6/17/2020 10:15 AM
 */

public class j {
    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(2,3,4,5,3);
        Stream<Integer> integerStream = number.stream().map(x -> x * x);
        List<Integer> collect = integerStream.collect(Collectors.toList());

        System.out.println();


        Set square = integerStream.collect(Collectors.toSet());



//        List d = Arrays.asList(2,3,4,5);
//        int even = d.stream().filter(x->x%2==0)
//                .reduce()
    }
}
