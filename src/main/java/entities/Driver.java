package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private int birthyear;
    private String gender;

    @ManyToOne
    private Car car;

    public Driver() {
    }

    public Driver(String name, int birthyear, String gender) {
        this.name = name;
        this.birthyear = birthyear;
        this.gender = gender;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
        Driver driver = (Driver) o;
        return birthyear == driver.birthyear && Objects.equals(id, driver.id) && Objects.equals(name, driver.name) && Objects.equals(gender, driver.gender) && Objects.equals(car, driver.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthyear, gender, car);
    }
}
