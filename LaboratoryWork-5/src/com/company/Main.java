package com.company;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Person person1 = new Person("Дмитрий", "Корошев", "Григорьевич", 2003);
//        Person person2 = new Person("Имя", "Фамилия", "Отчество", 1998);

//        new PersonWriter(person1, "./person1.ser");
//        new PersonWriter(person2, "./person2.ser");
//        PersonReader personReader = new PersonReader("./person1.ser");
//        PersonReader personReader1 = new PersonReader("./person2.ser");
//        personReader.print();
//        personReader1.print();

        People people = new People();
//        people.add(person1);
//        people.add(person2);

//        people.writeToFile("./people.txt");
        people.readFromFile("./people.txt");
//        System.out.println();
        people.print();
    }
}