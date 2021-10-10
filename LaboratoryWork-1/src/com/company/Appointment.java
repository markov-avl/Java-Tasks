package com.company;

public class Appointment {
    private TimeInterval interval;

    public Appointment(TimeInterval interval) {
        this.interval = interval;
    }

    public TimeInterval getTime() {
        return interval;
    }

    public boolean conflictsWith(Appointment other) {
        return this.interval.overlapsWith(other.getTime());
    }
}
