package com.god.economics.joobin.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WildcardDemo {
    public static void main(String[] args) {
        List<? super Integer> intList = new ArrayList<>();
        List<? super Number> numList = new ArrayList<>();


        //Lower Bounded Integer List
        List<?> list1 = Arrays.asList(12, 20.2, new D(24), "6", "7");

        //Integer list object is being passed 
        printOnlyIntegerClassorSuperClass3(list1);

        //Number list 
        List<Number> list2 = Arrays.asList(4.3, 5, 12.6, 7);

        //Integer list object is being passed 
        printOnlyIntegerClassorSuperClass(list2);
    }

    public static void printOnlyIntegerClassorSuperClass(List<? super Integer> list) {
        System.out.println((( (Double) list.get(0)).intValue()));
        System.err.println(list);
    }

    public static void printOnlyIntegerClassorSuperClass3(List<? extends Object> list) {
        System.err.println(list);
    }
}

class D {


    public D(int value) {
    }
}