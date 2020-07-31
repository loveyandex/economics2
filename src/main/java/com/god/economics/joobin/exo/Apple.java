package com.god.economics.joobin.exo;

public class Apple extends Fruit {
    public float getTotalCalories() {
        return 0.40f;
    }

    public String getOrigin() {
        return "someCity";
    }

    public static void main(String[] args) {
        Fruit fruit = new Apple();
        fruit.getOrigin();

    }
}

class Orange extends Fruit {
    public float getTotalCalories() {
        return 0.30f;
    }

    public String getOrigin() {
        return "someOtherCity";
    }


    public static void main(String[] args) {

    }
}