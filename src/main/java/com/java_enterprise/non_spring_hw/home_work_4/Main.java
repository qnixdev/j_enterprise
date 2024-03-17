package com.java_enterprise.non_spring_hw.home_work_4;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final int numberOfClients = 42;
        final int numberOfBartenders = 5;

        var bar = new Bar();

        var clientExecutor = Executors.newFixedThreadPool(numberOfClients);
        var bartenderExecutor = Executors.newFixedThreadPool(numberOfBartenders);

        try {
            for (int i = 1; i <= numberOfClients; i++) {
                int clientNumber = i;

                clientExecutor.execute(
                    () -> new Client(bar, String.format("Member_%d", clientNumber)).run()
                );
            }

            for (int i = 1; i <= numberOfBartenders; i++) {
                int bartenderNumber = i;

                bartenderExecutor.execute(
                    () -> new Bartender(bar, String.format("Bartender_%d", bartenderNumber)).run()
                );
            }
        } finally {
            clientExecutor.shutdown();
            bartenderExecutor.shutdown();
        }
    }
}