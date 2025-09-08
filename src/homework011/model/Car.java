package homework011.model;

public class Car {
    private String number;
    private String model;
    private String color;
    private long mileage;
    private long cost;

    public Car(String number, String model, String color, long mileage, long cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    public String getNumber() {
        return this.number;
    }

    public String getModel() {
        return this.model;
    }

    public String getColor() {
        return this.color;
    }

    public long getMileage() {
        return this.mileage;
    }

    public long getCost() {
        return this.cost;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String toString() {
        return this.number + " " + this.model + " " + this.color + " " + this.mileage + " " + this.cost;
    }
}
