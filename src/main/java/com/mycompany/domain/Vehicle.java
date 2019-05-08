package com.mycompany.domain;

public class Vehicle {

    private String regNum;
    private String make;
    private String colour;

    public Vehicle(){};

    public Vehicle(String regNum, String make, String colour) {
        this.regNum = regNum;
        this.make = make;
        this.colour = colour;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
