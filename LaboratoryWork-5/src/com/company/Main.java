package com.company;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("Дмитрий", "Корошев", "Григорьевич", 2003);
        new PersonWriter(person, "./person.ser");
        PersonReader personReader = new PersonReader("./person.ser");
        personReader.print();

        People people = new People();
        people.add(person);

        people.writeToFile("./people.txt");
        people.readFromFile("./people.txt");
        System.out.println();
        people.print();
    }
}