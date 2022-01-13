package ru.blogspot.feomatr.entity;

public enum Gender {
    FEMALE("Женский"),
    MALE("Мужской"),
    MALE2("Мужской");

    public static final String MALE_SUFFIX = "ич".trim().toLowerCase();
    public static final String FEMALE_SUFFIX = "на".trim().toLowerCase();

    private String name;

    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
