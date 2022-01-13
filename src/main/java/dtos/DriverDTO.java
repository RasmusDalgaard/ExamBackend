package dtos;

import entities.Car;
import entities.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverDTO {
    private int id;
    private String name;
    private int birthyear;
    private String gender;

    public DriverDTO() {
    }

    public DriverDTO(Driver driver) {
        this.id = driver.getId();
        this.name = driver.getName();
        this.birthyear = driver.getBirthyear();
        this.gender = driver.getGender();
    }

    public static List<DriverDTO> getDTOs(List<Driver> drivers){
        List<DriverDTO> driverDTOS = new ArrayList<>();
        if (drivers != null){
            drivers.forEach(driver -> driverDTOS.add(new DriverDTO(driver)));
        }
        return driverDTOS;
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

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
