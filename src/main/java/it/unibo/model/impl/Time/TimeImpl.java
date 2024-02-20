package it.unibo.model.impl.Time;

import java.util.*;
import java.beans.*;

import it.unibo.model.api.Time;
import it.unibo.model.impl.Management.AbstractManager;

public class TimeImpl implements Time {

    final static int TIME_FOR_15_MINUTES = 15_000;

    final static int STARTING_HOUR = 10;
    final static int STARTING_MIN = 0;

    final static int ENDING_HOUR = 22;
    final static int ENDING_MIN = 30;
    
    private static int workingDays = 1;
    private int hour;
    private int min;
    private Timer timer;
    private PropertyChangeSupport support;

    @Override
    public void incrementTime() {
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
                AbstractManager.addBalanceTot();
                support.firePropertyChange("day", null, TimeImpl.getWorkingDay());
            }
            AbstractManager.resetBalanceDay();
        }
        support.firePropertyChange("time", null, this.getHourAndMin());
    }

    private void startTimeForNewDay() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                incrementTime();
            }
            
        }, 0, 200);
    }

    @Override
    public void newDay() {
        this.support = new PropertyChangeSupport(this);
        this.hour = STARTING_HOUR;
        this.min = STARTING_MIN;
        this.timer = new Timer();
        startTimeForNewDay();
    }

    private boolean isEndOfDay() {
        return this.hour == ENDING_HOUR && this.min == ENDING_MIN ? true : false;
    }

    public static int getWorkingDay() {
        return workingDays;
    }

    private int getHour() {
        return this.hour;
    }

    private int getMin() {
        return this.min;
    }

    public String getHourAndMin() {
        if(this.min == 0) {
            return new String(this.getHour() + " : 00 ");
        } else {
            return new String(this.getHour() + " : " + this.getMin());
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
