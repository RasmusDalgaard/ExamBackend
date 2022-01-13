package dtos;

import entities.Driver;
import entities.Race;

import java.util.List;
import java.util.Objects;

public class RacesDTO {
    private List<RaceDTO> races;

    public RacesDTO(List<Race> races) {
        this.races = RaceDTO.getDTOs(races);
    }

    public List<RaceDTO> getRaces() {
        return races;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RacesDTO racesDTO = (RacesDTO) o;
        return Objects.equals(races, racesDTO.races);
    }

    @Override
    public int hashCode() {
        return Objects.hash(races);
    }
}
