package by.asalalaiko.repo;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class givenUsingTimer_whenSchedulingDailyTask_thenCorrect {

    @Test
    public void givenUsingTimer() throws InterruptedException{
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task1 performed on " + new Date());
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        //long period = 1000L * 60L * 60L * 24L;
        long period = 1000L ;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }

    @Test
    public void givenUsingExecutorService()
            throws InterruptedException {

        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                System.out.println("Task performed on " + new Date());
            }
        };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        long delay  = 1000L;
        long period = 1000L *10L;
        executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(delay + period * 5);
        executor.shutdown();
    }
}
