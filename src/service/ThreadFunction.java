package service;

/**
 * Created by StReaM on 2/1/2016.
 */
public class ThreadFunction {


    public static long getSleepTimeByLevel(int level) {
        long sleep = (-40 * level + 800);
        sleep = sleep < 100 ? 100:sleep;
        return sleep;
    }
}