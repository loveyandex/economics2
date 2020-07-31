package com.god.economics.joobin.functional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * created By gOD on 6/18/2020 11:55 AM
 */

public class UseFunc<T extends Throwable,R> implements  Func<T,R>{
    @Override
    public R apply(T t) {
        return null;
    }

    public static <T extends Comparable<T>> int compare(T t1, T t2){
        return t1.compareTo(t2);
    }
    public static void main(String[] args) {

        UseFunc<Throwable, Throwable> objectThrowableUseFunc = new UseFunc<>();

        Throwable apply = objectThrowableUseFunc.apply(new RuntimeException());
        int compare = objectThrowableUseFunc.compare(250, 2005);


        List<Integer> integers = Arrays.asList(12, 1, 34, 12, 531, 125423, 23404, 3454);
        DoubleStream parallel = integers.stream().map(new Func<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer.toString();
            }
        }).mapToDouble(Double::parseDouble).parallel();

        double v = parallel.average().orElseThrow(() -> new RuntimeException("fdf"));
        System.out.println(v);

    }
}
