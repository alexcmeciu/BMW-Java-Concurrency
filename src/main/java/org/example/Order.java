package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Order implements Runnable{
    Logger logger=LoggerFactory.getLogger(Order.class);
    private int id;
    private boolean processedWithSucces;

    private final AtomicInteger succesOrders;
    private final AtomicInteger failedOrders;

    public Order(int id,AtomicInteger succesOrders, AtomicInteger failedOrders){
        this.id=id;
        this.succesOrders=succesOrders;
        this.failedOrders=failedOrders;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(50);
            Random random=new Random();
            this.processedWithSucces=random.nextBoolean();
            if(this.processedWithSucces){
                succesOrders.incrementAndGet();
                logger.info("Order successfully processed.");
            }
            else{
                failedOrders.incrementAndGet();
                logger.error("Order processing failed.");
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
