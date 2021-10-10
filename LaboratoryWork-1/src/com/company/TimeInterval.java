package com.company;

public class TimeInterval {
    private int start;
    private int end;

    public TimeInterval(int start, int end) {
        // if start is negative, start sets as 0
        this.start = Math.max(start, 0);
        // if end is lower than start, end sets as start
        this.end = Math.max(end, this.start);
    }

    public boolean overlapsWith(TimeInterval interval) {
        return !(this.start > interval.end || this.end < interval.start);
    }
}
