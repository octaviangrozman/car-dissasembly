package models;

public class Car {

    private int chassisNo;
    private String model;
    private double weight;


    public Car(int chassisNo, String model, double weight) {
        this.chassisNo = chassisNo;
        this.model = model;
        this.weight = weight;
    }

    public int getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(int chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
