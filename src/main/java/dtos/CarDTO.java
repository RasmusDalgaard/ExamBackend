package dtos;


import entities.Car;
import entities.Race;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {
    private int id;
    private String name;
    private String brand;
    private String make;
    private int year;
    private List<DriverDTO> driverDTOs;
    private List<RaceDTO> raceDTOS;

    public CarDTO(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.brand = car.getBrand();
        this.make = car.getMake();
        this.year = car.getYear();
        this.driverDTOs = DriverDTO.getDTOs(car.getDrivers());
        this.raceDTOS = RaceDTO.getDTOs(car.getRaces());
    }

    public static List<CarDTO> getDTOs (List<Car> cars) {
        List<CarDTO> carDTOS = new ArrayList<>();
        if (cars != null){
            cars.forEach(car -> carDTOS.add(new CarDTO(car)));
        }
        return carDTOS;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public List<DriverDTO> getDriverDTOs() {
        return driverDTOs;
    }

    public List<RaceDTO> getRaceDTOS() {
        return raceDTOS;
    }
}
