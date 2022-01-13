package dtos;

import entities.Driver;
import entities.Race;

import java.util.ArrayList;
import java.util.List;

public class DriverDTO {
    private int id;
    private String name;
    private int birthyear;
    private String gender;
    private CarDTO carDTO;

    public DriverDTO(Driver driver) {
        this.id = driver.getId();
        this.name = driver.getName();
        this.birthyear = driver.getBirthyear();
        this.gender = driver.getGender();
        this.carDTO = new CarDTO(driver.getCar());
    }

    public static List<DriverDTO> getDTOs (List<Driver> drivers) {
        List<DriverDTO> driverDTOS = new ArrayList<>();
        if (drivers != null){
            drivers.forEach(driver -> driverDTOS.add(new DriverDTO(driver)));
        }
        return driverDTOS;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public String getGender() {
        return gender;
    }

    public CarDTO getCarDTO() {
        return carDTO;
    }
}
