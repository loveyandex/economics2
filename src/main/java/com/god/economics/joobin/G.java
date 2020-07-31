package com.god.economics.joobin;

import java.util.ArrayList;
import java.util.List;

/**
 * created By gOD on 6/17/2020 10:13 PM
 */

public class G {
    public static void main(String[] args) {
        List<Object> list = new ArrayList();
        list.add("abc");
        list.add(new Integer(5)); //OK

        for(Object obj : list){
            //type casting leading to ClassCastException at runtime
            String str=(String) obj.toString();
        }
    }
}
