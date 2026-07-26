package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static void main(String[] args) {
        AtomicInteger totalProcessedOrders=new AtomicInteger(0);
        AtomicInteger totalFailedOrders=new AtomicInteger(0);

        ExecutorService executor= Executors.newFixedThreadPool(18);

        for(int i=1;i<=100;i++){
            Order order=new Order(i,totalProcessedOrders,totalFailedOrders);
            executor.submit(order);
        }
        executor.shutdown();
        while(!executor.isTerminated()){}
        System.out.println("Orders processed with succes: "+ totalProcessedOrders);
        System.out.println("Orders failed: "+ totalFailedOrders);
    }
}