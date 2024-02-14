package it.unibo.model.impl.Time;

import java.util.*;

import it.unibo.model.impl.Management.AbstractManager;

public class TimeImp {

    final static int TIME_FOR_15_MINUTES = 15000; // millisecondi per far passare 15 minuti nel gioco

    final static int STARTING_HOUR = 10;
    final static int STARTING_MIN = 0;

    final static int ENDING_HOUR = 22;
    final static int ENDING_MIN = 30;
    
    public int workingDays = 1;
    private int hour;
    private int min;
    private Timer timer;

    public TimeImp() {
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

                // System.out.println("Orario: Ora : [ " + getHour() + " ] \t Minuti : [ " + getMin() + " ]\n");

                if(isEndOfDay()) {
                    // System.out.println("FINE GIORNATA!\nOrario: Ora : [ " + getHour() + " ] \t Minuti : [ " + getMin() + " ]\n");
                    timer.cancel();
                    if(AbstractManager.levelPassed()) {
                        workingDays++;
                    }
                }
            }
            
        };

        timer.scheduleAtFixedRate(task, 0, TIME_FOR_15_MINUTES); // test with 1000ms
    }

    public void newDay() {
        new TimeImp();
        startTimeForNewDay();
    }

    private boolean isEndOfDay() {
        return this.hour == ENDING_HOUR && this.min == ENDING_MIN ? true : false;
    }
}
