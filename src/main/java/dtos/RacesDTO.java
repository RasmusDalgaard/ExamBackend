package dtos;

import entities.Driver;
import entities.Race;

import java.util.List;

public class RacesDTO {
    private List<RaceDTO> races;

    public RacesDTO(List<Race> races) {
        this.races = RaceDTO.getDTOs(races);
    }
}
