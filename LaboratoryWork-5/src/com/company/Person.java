package com.company;

import java.io.Serial;
import java.io.Serializable;

public record Person(String firstName, String lastName, String fatherName, int yearOfBirth) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return  "firstName: " + firstName +
                "\nlastName: " + lastName +
                "\nfatherName: " + fatherName +
                "\nyearOfBirth: " + yearOfBirth;
    }
}