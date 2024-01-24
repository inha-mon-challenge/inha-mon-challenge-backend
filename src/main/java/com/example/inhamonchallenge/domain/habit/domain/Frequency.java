package com.example.inhamonchallenge.domain.habit.domain;

public enum Frequency {
    DAILY("매일"),
    WEEKLY("매주");

    private String description;

    Frequency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static Frequency fromString(String description) {
        for (Frequency value : Frequency.values()) {
            if (value.description.equals(description)) {
                return value;
            }
        }

        throw new IllegalArgumentException("No constant with text " + description + " found");
    }
}