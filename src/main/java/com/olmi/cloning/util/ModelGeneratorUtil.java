package com.olmi.cloning.util;

import com.olmi.cloning.model.*;

import java.util.*;

public class ModelGeneratorUtil {

    private ModelGeneratorUtil() {
    }

    public static Person getTestedPerson() {
        Address addressFirst = getAddress("Улица Иркутская", "8А", 3, 23);
        Pet cat = getPet("Муся", 1);
        Pet dog = getPet("Мухтар", 10);
        Person grandChild1 = getPerson("Максим", 5, Sex.MAN, addressFirst, null,
                Arrays.asList("Буратино", "Сказки Перо", "Гарри Поттер"),
                new HashSet<>(Arrays.asList("понедельник", "вторник")),
                new HashMap<>() {{
                    put("Кошка", cat);
                    put("Собака", dog);
                }}
        );
        Person grandChild2 = getPerson("Соня", 1, Sex.WOMAN, addressFirst, null,
                Arrays.asList("Буратино", "Сказки Перо"),
                new HashSet<>(Arrays.asList("понедельник", "вторник", "среда")),
                new HashMap<>() {{
                    put("Кошка", cat);
                    put("Собака", dog);
                }}
        );
        Person child1 = getPerson("Иван", 30, Sex.MAN, addressFirst,
                Arrays.asList(grandChild1, grandChild2).toArray(new Person[0]),
                Arrays.asList("Метро", "1984"), new HashSet<>(Arrays.asList("суббота", "воскресенье")),
                new HashMap<>() {{
                    put("Кошка", cat);
                    put("Собака", dog);
                }}
        );
        Address addressSecond = getAddress("Улица Некрасова", "105", 10, 172);
        Person child2 = getPerson("Мария", 20, Sex.WOMAN, addressSecond, null, null,
                new HashSet<>(List.of("среда")),
                null
        );
        return getPerson("Лариса", 60, Sex.WOMAN, addressSecond,
                Arrays.asList(child1, child2).toArray(new Person[0]), Arrays.asList("Сказки Пушкина", "1984"),
                null,
                null
        );
    }

    private static Address getAddress(String street, String buildingNumber, int floor, int flatNumber) {
        return Address.builder()
                .street(street)
                .building(Building.builder()
                        .buildingNumber(buildingNumber)
                        .floor(floor)
                        .flatNumber(flatNumber)
                        .build())
                .build();
    }

    private static Pet getPet(String name, int age) {
        return Pet.builder()
                .name(name)
                .age(age)
                .build();
    }

    private static Person getPerson(String name, int age, Sex sex, Address address, Person[] children,
                                    List<String> favoriteBooks, Set<String> favoriteDays, Map<String, Pet> pets) {
        return Person.builder()
                .name(name)
                .age(age)
                .sex(sex)
                .address(address)
                .children(children)
                .favoriteBooks(favoriteBooks)
                .favoriteDays(favoriteDays)
                .pets(pets)
                .build();
    }
}
