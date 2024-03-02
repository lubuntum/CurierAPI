package com.example.curierplus.enities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Food implements Serializable {
    public String name;
    public Float price;

    public Food(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s, цена: %s",name, price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
