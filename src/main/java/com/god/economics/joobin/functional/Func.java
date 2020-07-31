package com.god.economics.joobin.functional;

import java.util.function.Function;

/**
 * created By gOD on 6/18/2020 11:27 AM
 */
@FunctionalInterface
public interface Func<T,R> extends Function<T,R> {

    R apply(T t);


    default <V> Func<V,R> compose(Func<? super V,? extends T> before){

        return (V v)->apply(before.apply(v));

    }

}
