package it.unibo.model.impl;

import it.unibo.model.api.Oven;

import java.time.LocalTime;
import java.util.*;


public class OvenImpl implements Oven{

    final static long COOKNING_TIME_IN_SECONDS = 5;
    final static long COOKNING_TIME_IN_MICROSECONDS = 5000;

    private boolean emptyOven;
    private boolean isPizzaCooked;
    private Timer ovenTimer;
    private LocalTime finishBakingTime;


    public OvenImpl(){
        emptyOven = true;
        ovenTimer = new Timer();
        isPizzaCooked = false;
        finishBakingTime = LocalTime.now().plusSeconds(COOKNING_TIME_IN_SECONDS);
    }

    @Override
    public boolean isOvenEmpty() {
        return emptyOven ? true : false;
    }

    @Override
    public void baking() {
        emptyOven = false;

        TimerTask ovenTask = new TimerTask() {

            @Override
            public void run() {

                if (LocalTime.now().getSecond() == finishBakingTime.getSecond()){
                    isPizzaCooked = true;
                    ovenTimer.cancel();
                }
            }
           
        };

        ovenTimer.scheduleAtFixedRate(ovenTask, 0, COOKNING_TIME_IN_MICROSECONDS);
    }
}