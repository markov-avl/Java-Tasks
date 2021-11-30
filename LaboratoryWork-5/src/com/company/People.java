package com.company;

import java.io.*;
import java.util.ArrayList;

public class People {
    private final ArrayList<Person> people = new ArrayList<>();

    void add(Person person) {
        people.add(person);
    }

    public String parseValue(String line) {
        return line.split(": ")[1];
    }

    void writeToFile(String path) throws IOException {
        FileWriter outfile = new FileWriter(path);
        for (int i = 0; i < people.size(); ++i) {
            outfile.write(people.get(i).toString());
            if (i + 1 < people.size()) {
                outfile.write("\n\n");
            }
        }
        outfile.close();
    }

    void readFromFile(String path) throws IOException {
        people.clear();
        FileReader infile = new FileReader(path);
        BufferedReader reader = new BufferedReader(infile);
        String line = "";
        while (line != null) {
            people.add(new Person(
                    parseValue(reader.readLine()),
                    parseValue(reader.readLine()),
                    parseValue(reader.readLine()),
                    Integer.parseInt(parseValue(reader.readLine()))
            ));
            line = reader.readLine();
        }
        infile.close();
        reader.close();
    }

    void print() {
        for (int i = 0; i < people.size(); ++i) {
            System.out.println(people.get(i));
            if (i + 1 < people.size()) {
                System.out.println();
            }
        }
    }
}