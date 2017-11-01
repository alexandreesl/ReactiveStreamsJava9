package com.alexandreesl.handson.consumer;

import com.alexandreesl.handson.model.Order;

import static java.util.concurrent.Flow.Subscriber;
import static java.util.concurrent.Flow.Subscription;


public class CDOrderConsumer implements Subscriber<Order> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {

        this.subscription = subscription;
        subscription.request(1);

    }

    @Override
    public void onNext(Order item) {
        System.out.println("I am sending the Order to the CD!");
        subscription.request(1);

    }

    @Override
    public void onError(Throwable throwable) {

        throwable.printStackTrace();

    }

    @Override
    public void onComplete() {

        System.out.println("All the orders were processed!");

    }
}
