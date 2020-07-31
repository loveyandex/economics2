package com.god.economics.joobin;

import com.god.economics.crawllers.instagram.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamDemo {
 
    public static void main(String[] args) {
 
        long t1, t2;
        List<Employee> eList = new ArrayList<Employee>();
        for(int i=0; i<1000; i++) {
            eList.add(new Employee("A", 20000));
            eList.add(new Employee("B", 3000));
            eList.add(new Employee("C", 15002));
            eList.add(new Employee("D", 7856)); 
            eList.add(new Employee("E", 200)); 
            eList.add(new Employee("F", 50000));
        }
 
        /***** Here We Are Creating A 'Sequential Stream' & Displaying The Result *****/
        t1 = System.currentTimeMillis();
        long count = eList.stream().filter(e -> e.getSalary() > 15000).mapToLong(value -> 1L).count();
        System.out.println("Sequential Stream Count?= " + count);
 
        t2 = System.currentTimeMillis();
        System.out.println("Sequential Stream Time Taken?= " + (t2-t1) + "\n");
 
        /***** Here We Are Creating A 'Parallel Stream' & Displaying The Result *****/
        t1 = System.currentTimeMillis();
        Stream<Employee> employeeStream = eList.parallelStream().filter(e -> e.getSalary() > 15000);


        System.out.println("Parallel Stream Count?= " + employeeStream.count());
 
        t2 = System.currentTimeMillis();
        System.out.println("Parallel Stream Time Taken?= " + (t2-t1));
    }
}