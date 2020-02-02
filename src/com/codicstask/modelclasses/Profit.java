package com.codicstask.modelclasses;

public class Profit {
    private int count; // quantity of goods sold
    private double price; // total amount

    public Profit(int count, double price) {
        this.count = count;
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return " only " + count +
                " cars in the amount of " + price;
    }
}
