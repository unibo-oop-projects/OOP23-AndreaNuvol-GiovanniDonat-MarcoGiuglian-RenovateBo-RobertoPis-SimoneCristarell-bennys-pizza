package it.unibo.model.api;

/**
 * Interface of the time.
 */
public interface Time {
    /**
     * Reset time and start a new day.
     */
    void newDay();

    /**
     * Allows to increase the time by "virtual" 15 minutes every 15 real seconds.
     */
    void incrementTime();

    /**
     * @return a string with the "virtual" time.
     */
    String getHourAndMin();

    /**
     * @return the resoult of the working day.
     */
    String getResult();
}
