package com.codicstask.modelclasses;

public class CarAdditional {

    private CarModel model;
    private int count;

    public CarAdditional(CarModel model, int count) {
        this.model = model;
        this.count = count;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
