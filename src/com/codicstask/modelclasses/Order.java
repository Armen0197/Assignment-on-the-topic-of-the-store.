package com.codicstask.modelclasses;

public class Order {

    private long id; // unique id
    private long customerId; // to whom they sold
    private long employeeId; // who sold
    private long[] cars; // list of numbers of cars that sold

    public Order(long id, long employeeId, long customerId, long[] cars) {
        this.id = id;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long[] getCars() {
        return cars;
    }

    public void setCars(long[] cars) {
        this.cars = cars;
    }
}
