package io.paul.clock;


import java.time.LocalDateTime;

public class FakeClock implements Clock {
    private final int year ;
    private final int month;
    private final int dayOfMonth;
    private final int hour ;
    private final int minute ;
    private final int second;

    public FakeClock(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    public LocalDateTime now() {

        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
    }
}
