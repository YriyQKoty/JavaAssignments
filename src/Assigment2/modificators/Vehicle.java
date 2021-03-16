package Assigment2.modificators;


//клас для демонстрації роботи модифікаторів
public class Vehicle {
    int velocity;
    String model;
    String name;


    public String getName() {
        return this.name;
    }

    void setVelocity(int value) {
        velocity = value;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                ", model=" + model +
                ", velocity=" + velocity +
                '}';
    }
}
