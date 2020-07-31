package com.god.economics.joobin;

/**
 * created By gOD on 6/17/2020 10:20 PM
 */

public class GenericMethod {

    public static <S> S get(S s){
        return s;
    }

    public static void main(String[] args) {
        String king = GenericMethod.get("king");

    }

}
