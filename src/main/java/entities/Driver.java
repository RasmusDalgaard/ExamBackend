package entities;

import javax.persistence.*;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private int birthyear;
    private String gender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;

    public Driver() {

    }

    public Driver(String name, int birthyear, String gender, Car car) {
        this.name = name;
        this.birthyear = birthyear;
        this.gender = gender;
        this.car = car;
    }

    public Driver(String name, int birthyear, String gender) {
        this.name = name;
        this.birthyear = birthyear;
        this.gender = gender;
        this.car = null;
    }

    public void addCar(Car car) {
        if (car != null && this.getCar() == null) {
            this.setCar(car);
            car.getDrivers().add(this);
        }
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
}
