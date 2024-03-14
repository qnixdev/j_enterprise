package com.java_enterprise.home_work_4;

public class Main {
    public static void main(String[] args) {
        final int numberOfClients = 25;
        final int numberOfBartenders = 4;

        var bar = new Bar(numberOfClients, numberOfBartenders);

        for (int i = 1; i <= numberOfClients; i++) {
            new Thread(new Client(bar, String.format("Member_%d", i))).start();
        }
        for (int i = 1; i <= numberOfBartenders; i++) {
            new Thread(new Bartender(bar, String.format("Bartender_%d", i))).start();
        }
    }
}