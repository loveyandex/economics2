package com.god.economics.joobin.functional;

import java.util.function.BiFunction;

/**
 * created By gOD on 6/17/2020 10:46 AM
 */

public class BiFuncImpl<T> implements BiFunction<Integer, Integer, T> {

    @Override
    public T apply(Integer integer, Integer integer2) {
        return ((T) (Integer) (integer + integer2));
    }

    private static class A<T> {
        int i = 1;
        BiFuncImpl<T> biFunc;

        public A() {
            biFunc= new BiFuncImpl<>();
        }
    }

    public A<T> useA() {
        return new A<T>();
    }

    public static <T> A<T> useAstatic() {
        return new A<T>();
    }

    public static void main(String[] args) {
        A<Double> objectA = BiFuncImpl.useAstatic();
        BiFuncImpl<Double> biFunc = objectA.biFunc;
        Double apply = biFunc.apply(10, 23);
        System.out.println(apply);
    }
}
