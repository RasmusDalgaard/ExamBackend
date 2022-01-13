package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String brand;
    private String make;
    private int year;

    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
    private List<Driver> drivers;

    @ManyToMany(mappedBy = "cars")
    private List<Race> races;

    public Car() {

    }

    public Car(String name, String brand, String make, int year) {
        this.name = name;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.drivers = new ArrayList<>();
        this.races = new ArrayList<>();
    }

    public void addDriverToCar(Driver driver) {
        if (driver != null) {
            this.drivers.add(driver);
            driver.setCar(this);
        }
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

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
