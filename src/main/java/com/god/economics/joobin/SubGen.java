package com.god.economics.joobin;

import java.util.List;

/**
 * created By gOD on 6/17/2020 10:50 PM
 */

public interface SubGen<E, T> extends List<E> {
    T get();

    <F> T met(F f);

    <F> T pet(F f);

    T pet(F f);


    class F<E, T> {
        private F f;

    }

}

class F<E, T> {
    private SubGen.F f;

}
