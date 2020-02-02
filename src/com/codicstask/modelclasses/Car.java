package com.codicstask.modelclasses;

public class Car {

    private long id; // unique id
    private String carName;
    private String cityOfProduction;
    private double price;
    private CarModel model;

    public Car(long id, String carName, String cityOfProduction, double price, CarModel model) {
        this.id = id;
        this.carName = carName;
        this.cityOfProduction = cityOfProduction;
        this.price = price;
        this.model = model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCityOfProduction() {
        return cityOfProduction;
    }

    public void setCityOfProduction(String cityOfProduction) {
        this.cityOfProduction = cityOfProduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }
}
