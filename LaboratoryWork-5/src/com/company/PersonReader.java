package com.company;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PersonReader {
    private final Person person;

    public PersonReader(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        person = (Person) objectInputStream.readObject();
    }

    public void print() {
        System.out.println(person);
    }
}
