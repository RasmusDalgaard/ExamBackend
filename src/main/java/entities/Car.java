package entities;

import dtos.CarDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car ")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String brand;
    private String make;
    private int year;

    @ManyToMany(mappedBy = "cars", cascade = CascadeType.PERSIST)
    List<Race> races;

    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
    private List<Driver> drivers;

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

    public void addRace(Race race) {
        if (race != null) {
            this.races.add(race);
            race.getCars().add(this);
        }
    }

    public void addDriver(Driver driver) {
        if (driver != null) {
            this.drivers.add(driver);
            driver.setCar(this);
        }
    }


    public void removeDrivers() {
        for (Iterator<Driver> iterator = this.drivers.iterator(); iterator.hasNext();) {
            Driver driver = iterator.next();
            iterator.remove();
            driver.setCar(null);
        }
    }

    public void removeRaces() {
        for (Iterator<Race> iterator = this.races.iterator(); iterator.hasNext();) {
            Race race = iterator.next();
            iterator.remove();
            race.getCars().remove(this);
        }
    }



    public static Car getEntity(CarDTO carDTO) {
        if (carDTO != null) {
            Car car = new Car(carDTO.getName(), carDTO.getBrand(), carDTO.getMake(), carDTO.getYear());
            return car;
        }
        return null;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && Objects.equals(id, car.id) && Objects.equals(name, car.name) && Objects.equals(brand, car.brand) && Objects.equals(make, car.make) && Objects.equals(drivers, car.drivers) && Objects.equals(races, car.races);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, make, year, drivers, races);
    }
}
