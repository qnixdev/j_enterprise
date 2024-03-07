package com.java_enterprise.home_work_2;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ValueCalculator calculator1 = new ValueCalculator(1_000_000);
        calculator1.setNumberOfThreads(2);
        calculator1.compute();

        ValueCalculator calculator2 = new ValueCalculator(1_000_000);
        calculator2.setNumberOfThreads(10);
        calculator2.shuffleArray();
        calculator2.findMaxValue();
    }
}