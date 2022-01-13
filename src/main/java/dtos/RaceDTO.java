package dtos;

import entities.Car;
import entities.Race;

import java.util.ArrayList;
import java.util.List;

public class RaceDTO {
    private int id;
    private String name;
    private String date;
    private double time;
    private String location;

    public RaceDTO(Race race) {
        this.id = race.getId();
        this.name = race.getName();
        this.date = race.getDate();
        this.time = race.getTime();
        this.location = race.getLocation();
    }

    public static List<RaceDTO> getDTOs(List<Race> races){
        List<RaceDTO> raceDTOS = new ArrayList<>();
        if (races != null){
            races.forEach(race -> raceDTOS.add(new RaceDTO(race)));
        }
        return raceDTOS;
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

    @Override
    public String toString() {
        return "RaceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }
}
