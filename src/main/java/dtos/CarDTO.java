package dtos;

import entities.Car;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {
    private int id;
    private String name;
    private String brand;
    private String make;
    private int year;

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.brand = car.getBrand();
        this.make = car.getMake();
        this.year = car.getYear();
    }

    public static List<CarDTO> getDTOs(List<Car> cars){
        List<CarDTO> carDTOS = new ArrayList<>();
        if (cars != null){
            cars.forEach(car -> carDTOS.add(new CarDTO(car)));
        }
        return carDTOS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
