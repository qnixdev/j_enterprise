package com.task_management_system.non_spring_hw.home_work_3;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Bar {
    private final int numberOfClients;
    private final int numberOfBartenders;
    private final BlockingDeque<String> orders;

    public Bar(
        int numberOfClients,
        int numberOfBartenders
    ) {
        this.numberOfClients = numberOfClients;
        this.numberOfBartenders = numberOfBartenders;
        this.orders = new LinkedBlockingDeque<>();
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public int getNumberOfBartenders() {
        return numberOfBartenders;
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

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        System.out.printf("The '%s' in processing order '%s'.%n", bartender, order);

        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        this.finishOrder(bartender, order);
    }

    private void finishOrder(String bartender, String order) {
        System.out.printf("The '%s' finish creating order '%s'.%n", bartender, order);
    }
}