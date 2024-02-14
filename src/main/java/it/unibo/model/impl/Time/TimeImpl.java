package it.unibo.model.impl.Time;

import java.util.*;

import it.unibo.model.impl.Management.AbstractManager;

public class TimeImpl {

    final static int TIME_FOR_15_MINUTES = 15_000;

    final static int STARTING_HOUR = 10;
    final static int STARTING_MIN = 0;

    final static int ENDING_HOUR = 22;
    final static int ENDING_MIN = 30;
    
    private int workingDays = 1;
    private int hour;
    private int min;
    private Timer timer;

    public TimeImpl() {
        this.hour = STARTING_HOUR;
        this.min = STARTING_MIN;
        this.timer = new Timer();
    }

    private void startTimeForNewDay() {
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                if(min == 45) {
                    min = 0;
                    hour++;
                } else {
                    min += 15;
                }

                if(isEndOfDay()) {
                    timer.cancel();
                    if(AbstractManager.levelPassed()) {
                        workingDays++;
                    }
                }
            }
            
        };

        timer.scheduleAtFixedRate(task, 0, TIME_FOR_15_MINUTES);
    }

    public void newDay() {
        new TimeImpl();
        startTimeForNewDay();
    }

    private boolean isEndOfDay() {
        return this.hour == ENDING_HOUR && this.min == ENDING_MIN ? true : false;
    }

    public int getWorkingDay() {
        return this.workingDays;
    }
}
