package com.company.task2.entity;

public enum Country {
    BY("Belarus"),
    RU("Russia"),
    UA("Ukraine");

    private String fullName;

    Country(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
