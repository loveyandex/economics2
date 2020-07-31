package com.god.economics.crawllers.instagram.donyacomment;

import java.util.Arrays;
import java.util.List;

/**
 * created By gOD on 6/23/2020 11:23 PM
 */

public class Fed {


    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(23, 32, 254, 2, 33094, 34, 34, 34,
                43, 5, 4, 1, 4, 4, 23, 23, 45,
                35, 32, 4, 5, 4, 3);

        Object[] objects = integers.stream().filter(integer -> integer>23).limit(2).toArray();
        System.out.println(objects.length);


    }
}
