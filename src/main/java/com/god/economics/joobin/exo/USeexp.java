package com.god.economics.joobin.exo;

import java.util.ArrayList;

/**
 * created By gOD on 6/18/2020 8:56 PM
 */

public class USeexp {

    public void j() throws Exp {
        i(true);
    }


    public void i(boolean b) throws Exp {
        if (b)
            throw new Exp("king in the nirth");
    }


    public static void main(String[] args) {

        ArrayList<String> strings = new ArrayList<>();
        strings.add("23");
        try {
            strings.get(1);
            new USeexp().i(true);
        }catch (RuntimeException r){
            System.out.println("in catch");
        }finally {
            System.out.println("financllyNullPointerExceptionEnumeration");
        }
    }
}
