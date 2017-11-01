package com.alexandreesl.handson.processor;

import com.alexandreesl.handson.model.Order;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;


public class OrderFilter extends SubmissionPublisher<Order> implements Flow.Processor<Order, Order> {

    private Flow.Subscription subscription;


    @Override
    public void onSubscribe(Flow.Subscription subscription) {

        this.subscription = subscription;
        subscription.request(1);

    }

    @Override
    public void onNext(Order item) {

        if (item.getTotal().doubleValue() > 0) {

            submit(item);

        } else {

            System.out.println("INVALID ORDER! DISCARDING...");

        }

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
