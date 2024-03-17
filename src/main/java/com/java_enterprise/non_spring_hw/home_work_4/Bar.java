package com.java_enterprise.non_spring_hw.home_work_4;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Bar {
    private final BlockingDeque<String> orders;

    public Bar() {
        this.orders = new LinkedBlockingDeque<>();
    }

    public BlockingDeque<String> getOrders() {
        return orders;
    }

    public void createOrder(String client, String order) {
        try {
            this.orders.put(order);

            System.out.printf("The '%s' create new order '%s'.%n", client, order);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void processOrder(String bartender, String order) {
        System.out.printf("The '%s' take order '%s'.%n", bartender, order);
        System.out.printf("The '%s' in processing order '%s'.%n", bartender, order);
        System.out.printf("The '%s' finish creating order '%s'.%n", bartender, order);
    }
}