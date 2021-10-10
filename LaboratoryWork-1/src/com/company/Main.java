package com.company;

// 2006 - 1 - A

public class Main {

    public static void main(String[] args) {
	    TimeInterval interval1 = new TimeInterval(15, 60);
        TimeInterval interval2 = new TimeInterval(0, 75);
        Appointment appointment1 = new Appointment(interval1);
        Appointment appointment2 = new Appointment(interval2);

        System.out.println(appointment1.conflictsWith(appointment2));
    }
}