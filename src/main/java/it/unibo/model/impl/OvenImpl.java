package it.unibo.model.impl;

import it.unibo.model.api.Oven;

import java.time.LocalTime;
import java.util.*;


public class OvenImpl implements Oven{

    final static long COOKNING_TIME_IN_SECONDS = 5;
    final static long COOKNING_TIME_IN_MICROSECONDS = 5000;

    private static boolean emptyOven;
    private static boolean isCooked;
    private static Timer ovenTimer;
    private static LocalTime finishBakingTime;


    public OvenImpl(){
        resetOven();
    }

    public static void resetOven(){
        emptyOven = true;
        ovenTimer = new Timer();
        isCooked = false;
        finishBakingTime = LocalTime.now().plusSeconds(COOKNING_TIME_IN_SECONDS);
    }

    @Override
    public boolean isOvenEmpty() {
        return emptyOven ? true : false;
    }

    @Override
    public void bakingPizza(){
        baking();
    }

    private void baking() {
        emptyOven = false;

        TimerTask ovenTask = new TimerTask() {

            @Override
            public void run() {

                if (LocalTime.now().getSecond() == finishBakingTime.getSecond()){
                    isCooked = true;
                    ovenTimer.cancel();
                }
            }
           
        };

        ovenTimer.scheduleAtFixedRate(ovenTask, 0, COOKNING_TIME_IN_MICROSECONDS);
    }

    @Override
    public boolean isPizzaCooked() {
        return isCooked;
    }
}