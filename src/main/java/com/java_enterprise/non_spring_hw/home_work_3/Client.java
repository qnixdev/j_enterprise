package com.java_enterprise.non_spring_hw.home_work_3;

public class Client implements Runnable {
    private final Bar bar;
    private final String clientName;

    public Client(
        Bar bar,
        String clientName
    ) {
        this.bar = bar;
        this.clientName = clientName;
    }

    @Override
    public void run() {
        bar.createOrder(this.clientName, Drink.getRandomDrink());
    }
}