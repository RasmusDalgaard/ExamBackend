package entities;

import dtos.RaceDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Race.deleteAllRows", query = "DELETE from Race ")

public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
    private String date;
    private double time;
    private String location;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Car> cars;

    public Race() {
    }

    public Race(int id, String name, String date, double time, String location) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.cars = new ArrayList<>();
    }

    public Race(String name, String date, double time, String location) {
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

    public static Race getEntity(RaceDTO raceDTO) {
        if (raceDTO != null) {
            Race race = new Race(raceDTO.getName(), raceDTO.getDate(), raceDTO.getTime(), raceDTO.getLocation());
            return race;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Double.compare(race.time, time) == 0 && Objects.equals(id, race.id) && Objects.equals(name, race.name) && Objects.equals(date, race.date) && Objects.equals(location, race.location) && Objects.equals(cars, race.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, time, location, cars);
    }
}
