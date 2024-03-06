package com.java_enterprise.home_work_2;

import java.util.Arrays;
import java.util.Random;

public class ValueCalculator {
    private double[] originArray;
    private int size;
    private int halfSize;
    private int numberOfThreads;

    public ValueCalculator(int size) {
        this.size = Math.max(size, 1_000_000);
        this.halfSize = (int) Math.floor(this.size / 2.);
        this.originArray = new double[this.size];
        this.numberOfThreads = 1;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public void compute() throws InterruptedException {
        var startAt = System.currentTimeMillis();

        Arrays.fill(this.originArray, 17.88);

        switch (this.numberOfThreads) {
            case 1:
                this.replaceValues(this.originArray);

                break;
            case 2:
                var firstChunk = new double[this.halfSize];
                var secondChunk = new double[this.size - this.halfSize];

                System.arraycopy(this.originArray, 0, firstChunk, 0, this.halfSize);
                System.arraycopy(this.originArray, this.halfSize, secondChunk, 0, this.size - this.halfSize);

                var firstThread = new Thread(() -> this.replaceValues(firstChunk));
                var secondThread = new Thread(() -> this.replaceValues(secondChunk));

                firstThread.start();
                firstThread.join();

                secondThread.start();
                secondThread.join();

                System.arraycopy(firstChunk, 0, this.originArray, 0, this.halfSize);
                System.arraycopy(secondChunk, 0, this.originArray, this.halfSize, this.size - this.halfSize);

                break;
            default:
                System.out.println("This logic only for check max two threads.");
        }

        var finishAt = System.currentTimeMillis();

        System.out.printf("For '%d' thread(s) process time is '%d'.%n", this.numberOfThreads, finishAt - startAt);
    }

    public void shuffleArray() {
        var randomizer = new Random();

        //Init random array
        for (int i = 0; i < this.originArray.length; i++) {
            this.originArray[i] = randomizer.nextDouble(this.originArray.length + i);
        }
        //Shuffling
        for (int i = this.originArray.length - 1; i > 0; i--) {
            int random = randomizer.nextInt(i + 1);
            double temp = this.originArray[random];
            this.originArray[random] = this.originArray[i];
            this.originArray[i] = temp;
        }
    }

    public void findMaxValue() throws InterruptedException {
        var startAt = System.currentTimeMillis();

        var tempChunks = new double[this.numberOfThreads];
        Thread[] threads = new Thread[this.numberOfThreads];

        var index = 0;
        var limit = (int) Math.floor(this.size / (float) this.numberOfThreads);

        do {
            var start = index * limit;
            var end = (this.numberOfThreads > 1 && index == this.numberOfThreads - 1)
                ? this.size - 1
                : ((this.numberOfThreads > 1 ? (index + 1) * limit : this.size) - 1)
            ;
            var i = index;

            threads[index] = new Thread(() -> tempChunks[i] = this.searchMaxValue(start, end));
            threads[index].start();
            threads[index].join();

            index++;
        } while (index < this.numberOfThreads);

        var max = Arrays.stream(tempChunks).max().orElse(Double.MIN_VALUE);

        var finishAt = System.currentTimeMillis();

        System.out.println();
        System.out.printf("Max value is '%f'.%n", max);
        System.out.printf("For '%d' thread(s) process time is '%d'.%n", this.numberOfThreads, finishAt - startAt);
    }

    private void replaceValues(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private double searchMaxValue(int start, int end) {
        return Arrays.stream(this.originArray, start, end)
            .max()
            .orElse(Double.MIN_VALUE)
        ;
    }
}