package it.unibo.model.impl;

import java.util.Timer;
import java.util.TimerTask;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import it.unibo.model.api.Time;
import it.unibo.model.impl.management.AbstractManager;
/**
 * Implementation of Time interface.
 */
public class TimeImpl implements Time {
    static final String WIN_STRING = "You win!";
    static final String LOSE_STRING = "You lose!";
    static final int TIME_FOR_15_MINUTES = 5_000;
    static final int STARTING_HOUR = 10;
    static final int STARTING_MIN = 0;
    static final int ENDING_HOUR = 22;
    static final int ENDING_MIN = 30;
    static final int LAST_HOUR_MIN = 45;
    static final int MIN_TO_ADD = 15;
    private String res = "";
    private int hour;
    private int min;
    private Timer timer = new Timer();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * It increments the current time of the day.
     */
    @Override
    public void incrementTime() {
        if (min == LAST_HOUR_MIN) {
            min = 0;
            hour++;
        } else {
            min += MIN_TO_ADD;
        }

        if (isEndOfDay()) {
            timer.cancel();
            setResult();
        }
        support.firePropertyChange("time", null, this.getHourAndMin());
    }

    private void setResult() {
        this.res = AbstractManager.levelPassed() ? WIN_STRING : LOSE_STRING;
        support.firePropertyChange("end", null, this.getResult());
    }

    /**
     * @return the result of the game.
     */
    @Override
    public String getResult() {
        return this.res;
    }

    private void startTimeForNewDay() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                incrementTime();
            }

        }, 0, TIME_FOR_15_MINUTES);
    }
    /**
     * It starts a new day.
     */
    @Override
    public void newDay() {
        this.support = new PropertyChangeSupport(this);
        this.hour = STARTING_HOUR;
        this.min = STARTING_MIN;
        this.timer.cancel();
        this.timer = new Timer();
        startTimeForNewDay();
    }

    private boolean isEndOfDay() {
        return this.hour == ENDING_HOUR && this.min == ENDING_MIN;
    }

    private int getHour() {
        return this.hour;
    }

    private int getMin() {
        return this.min;
    }

    /**
     * @return current hour and minute.
     */
    @Override
    public String getHourAndMin() {
        if (this.min == 0) {
            return this.getHour() + " : 00 ";
        } else {
            return this.getHour() + " : " + this.getMin();
        }
    }

    /**
     * It adds a property change listener to the support.
     * @param listener the property change listener.
     */
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
