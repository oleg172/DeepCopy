package com.olmi.cloning.model;

import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Getter
public class Person extends BasePerson {

    private Address address;
    private Person[] children;
    private List<String> favoriteBooks;
    private Set<String> favoriteDays;
    private Map<String, Pet> pets;
    private Person selfRef;

    @Builder
    public Person(String name, int age, Sex sex, Address address, Person[] children, List<String> favoriteBooks,
                  Set<String> favoriteDays, Map<String, Pet> pets) {
        super(name, age, sex);
        this.address = address;
        this.children = children;
        this.favoriteBooks = favoriteBooks;
        this.favoriteDays = favoriteDays;
        this.pets = pets;
        this.selfRef = this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(address, person.address) && Arrays.equals(children, person.children) && Objects.equals(favoriteBooks, person.favoriteBooks) && Objects.equals(favoriteDays, person.favoriteDays) && Objects.equals(pets, person.pets);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), address, favoriteBooks, favoriteDays, pets);
        result = 31 * result + Arrays.hashCode(children);
        return result;
    }
}
