package dtos;

import entities.Race;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RaceDTO {
    private int id;
    private String name;
    private Date date;
    private double time;
    private String location;
    private List<CarDTO> carDTOs;

    public RaceDTO(Race race) {
        this.id = race.getId();
        this.name = race.getName();
        this.date = race.getDate();
        this.time = race.getTime();
        this.location = race.getLocation();
        this.carDTOs = CarDTO.getDTOs(race.getCars());
    }

    public static List<RaceDTO> getDTOs (List<Race> races) {
        List<RaceDTO> raceDTOS = new ArrayList<>();
        if (races != null){
            races.forEach(race -> raceDTOS.add(new RaceDTO(race)));
        }
        return raceDTOS;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public double getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public List<CarDTO> getCarDTOs() {
        return carDTOs;
    }
}
