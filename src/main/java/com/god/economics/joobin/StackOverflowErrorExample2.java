package com.god.economics.joobin;

import java.util.ArrayList;

public class StackOverflowErrorExample2 {

    public static void recursivePrint(int num) {
        ArrayList<Integer> strings = new ArrayList<>();
        while (true) {
            strings.add(23 );
        }
    }

    public static void main(String[] args) {
        StackOverflowErrorExample2.recursivePrint(1);
    }
}