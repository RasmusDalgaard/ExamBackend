package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import dtos.RaceDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM--dd")
    private Date date;
    private double time;
    private String location;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Car> cars;

    public Race() {
    }

    public Race(String name, Date date, double time, String location) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.cars = new ArrayList<>();
    }

    public void addCarToRace(Car car) {
        if (car != null) {
            this.cars.add(car);
            car.getRaces().add(this);
        }
    }

    public static List<Race> getEntities(List<RaceDTO> raceDTOS) {
        List<Race> races = new ArrayList<>();
        if(raceDTOS != null){
            raceDTOS.forEach(raceDTO -> races.add(new Race(raceDTO.getName(), raceDTO.getDate(), raceDTO.getTime(), raceDTO.getLocation())));
        }
        return races;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
