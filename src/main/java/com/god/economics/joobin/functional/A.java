package com.god.economics.joobin.functional;

/**
 * created By gOD on 6/18/2020 8:09 PM
 */

public class A {

    public A() {

    }

    protected void common() {
        System.out.println("in A");

    }


    private boolean mis() {
        System.out.println("in mis");
        return false;
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        b.is();

        ((A) b).mis();

        a.common();
        ((B) a).is();


    }
}

class B extends A {

    boolean is() {
        return true;
    }

    @Override
    protected void common() {
        System.out.println("in B");
    }
}
