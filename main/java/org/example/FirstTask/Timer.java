package org.example.FirstTask;

import java.util.concurrent.TimeUnit;

public class Timer {
    public void showTime() {
        Thread timerThread = new Thread(() -> {
            for (int i = 1; ; i++) {
                try {
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread messageThread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("Минуло 5 секунд!");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timerThread.start();
        messageThread.start();
    }
}
