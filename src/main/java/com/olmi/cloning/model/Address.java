package com.olmi.cloning.model;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class Address {

    private String street;
    private Building building;

    public Address(String street, Building building) {
        this.street = street;
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(building, address.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, building);
    }
}
