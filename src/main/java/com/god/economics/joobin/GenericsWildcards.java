package com.god.economics.joobin;

import java.util.ArrayList;
import java.util.List;

public class GenericsWildcards {

	public static void main(String[] args) {
		List<Number> ints = new ArrayList<>();
		List<Integer> ints3 = new ArrayList<>();
		ints3.add(3); ints.add(5); ints.add(10);
		double sum = sum(ints3);
		System.out.println("Sum of ints="+sum);
	}

	public static double sum(List<? extends Number> list){
		double sum = 0;
		for(Number n : list){
			sum += n.doubleValue();
		}
		return sum;
	}
}