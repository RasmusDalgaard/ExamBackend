package dtos;

import entities.Car;
import entities.Driver;

import java.util.List;
import java.util.Objects;

public class DriversDTO {
    private List<DriverDTO> drivers;

    public DriversDTO(List<Driver> drivers) {
        this.drivers = DriverDTO.getDTOs(drivers);
    }

    public List<DriverDTO> getDrivers() {
        return drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriversDTO that = (DriversDTO) o;
        return Objects.equals(drivers, that.drivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drivers);
    }
}
