package com.god.economics.joobin.exo;

public abstract class Fruit implements Food {
    public float getTotalCalories() {
        return 0.50f;
    }

    public abstract String getOrigin();
}