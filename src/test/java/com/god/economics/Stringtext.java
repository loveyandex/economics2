package com.god.economics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stringtext {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> provinceAndtowns = new ArrayList<>();
        Scanner provinceAndtownsScanner = new Scanner(new File("provinceAndtowns.txt"));
        while (provinceAndtownsScanner.hasNextLine()) {
            provinceAndtowns.add(provinceAndtownsScanner.nextLine());
        }

        String d = "fff";
        dd(d);

        System.err.println(d);

    }

    public static void dd(String d) {
        d = d.replaceAll("ff", "goood");
        System.out.println(d);

    }


}
