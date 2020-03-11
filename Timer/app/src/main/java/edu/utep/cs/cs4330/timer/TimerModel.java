package edu.utep.cs.cs4330.timer;

public class TimerModel {
    private long startTime;
    private double randomDouble=0.0;

    public TimerModel(long time) {
        this.startTime = time;
    }

    public TimerModel() {
        startTime = System.currentTimeMillis();
    }

    /*
    private static final TimerModel theInstance = new TimerModel();
    private TimerModel(){

    }
    public static TimeModel getInstance(){
        return theInstance;
    }
    */

    public void startTimer() {
        randomDouble = Math.random() * 2000 + 1;
        System.out.println(randomDouble);
    }
    public void stopTimer() {
        startTime = 0;
    }
    public long elapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
    public boolean isRunning() {
        return startTime != 0;
    }

    public long getStatingTime() {
        return startTime;
    }
}
