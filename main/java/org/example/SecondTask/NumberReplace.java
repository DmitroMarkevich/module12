package org.example.SecondTask;

import java.util.Arrays;
import java.util.Objects;

public class NumberReplace{
    private String[] numbersOut;
    private final Object MONITOR = new Object();
    public String getNumbers(int number) {
        numbersOut = new String[number];

        Thread threadFizz = new Thread(() -> {
            for (int i = 0; i < number; i++) {
                if ((i+1) % 3 == 0) {
                    synchronized (MONITOR) {
                        numbersOut[i] = "fizz";
                    }
                }
            }
        });

        Thread threadBuzz = new Thread(() -> {
            for (int i = 0; i < number; i++) {
                if ((i+1) % 5 == 0) {
                    synchronized (MONITOR) {
                        numbersOut[i] = "buzz";
                    }
                }
            }
        });

        Thread threadFizzbuzz = new Thread(() -> {
            for (int i = 0; i < number; i++) {
                if (((i+1) % 3 == 0) && ((i+1) % 5 == 0)) {
                    synchronized (MONITOR) {
                        numbersOut[i] = "fizzbuzz";
                    }
                }
            }
        });

        Thread threadNumber = new Thread(() -> {
            for (int i = 0; i < number; i++) {
                if (((i+1) % 3 != 0) && ((i+1) % 5 != 0)) {
                    synchronized (MONITOR) {
                        numbersOut[i] = String.valueOf(i+1);
                    }
                }
            }
        });

        threadFizz.start();
        threadBuzz.start();
        threadFizzbuzz.start();
        threadNumber.start();

        return (Arrays.stream(numbersOut).filter(Objects::nonNull).count() == number)
                ? Arrays.toString(numbersOut) : null;
    }
}