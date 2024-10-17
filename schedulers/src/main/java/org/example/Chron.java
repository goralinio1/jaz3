package org.example;

import java.time.Duration;
import java.time.LocalDateTime;

public class Chron {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int maxExecutionTimes;
    private int executionTimes = 0;
    private LocalDateTime timeNow;
    private Duration intervalDuration;

    public static Chron builder(){return  new Chron();}

    public Chron setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        this.timeNow = startTime;
        return this;
    }
    public Chron setEndDate(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }
    public Chron setMaxExecutionTimes(int count) {
        this.maxExecutionTimes = count;
        return this;
    }
    public Chron setIntervalDuration(Duration interval) {
        this.intervalDuration = interval;
        return this;
    }
    public IProvideNextExecutionTime buildNextTimeExecutionProvider(){
        return () ->{
            if(executionTimes >= maxExecutionTimes || (timeNow!=null && timeNow.isEqual(endTime) || timeNow!=null && timeNow.isAfter(endTime))){
                return null;
            }

            if(timeNow != null)
                this.timeNow = timeNow.plus(intervalDuration);
            this.executionTimes = executionTimes+1;
            return timeNow;
        };
    }
}
