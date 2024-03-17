package com.java_enterprise.non_spring_hw.home_work_4;

public class Bartender implements Runnable {
    private final Bar bar;
    private final String bartenderName;

    public Bartender(
        Bar bar,
        String bartenderName
    ) {
        this.bar = bar;
        this.bartenderName = bartenderName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                bar.processOrder(this.bartenderName, bar.getOrders().take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}