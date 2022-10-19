package com.olmi.cloning.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class Building {

    private String buildingNumber;
    private int floor;
    private int flatNumber;

    public Building(String buildingNumber, int floor, int flatNumber) {
        this.buildingNumber = buildingNumber;
        this.floor = floor;
        this.flatNumber = flatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return floor == building.floor && flatNumber == building.flatNumber && Objects.equals(buildingNumber, building.buildingNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingNumber, floor, flatNumber);
    }
}
