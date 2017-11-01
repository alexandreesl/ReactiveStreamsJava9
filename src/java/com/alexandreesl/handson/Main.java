package com.alexandreesl.handson;

import com.alexandreesl.handson.consumer.CDOrderConsumer;
import com.alexandreesl.handson.model.Order;
import com.alexandreesl.handson.processor.OrderFilter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class Main {

    public static void main(String[] args) throws IOException {


        SubmissionPublisher<Order> submissionPublisher = new SubmissionPublisher<>();
        OrderFilter filter = new OrderFilter();
        submissionPublisher.subscribe(filter);
        filter.subscribe(new CDOrderConsumer());

        Order order = new Order();
        order.setId(1l);
        order.setOrderDate(new Date());
        order.setTotal(BigDecimal.valueOf(123));
        order.setProducts(List.of("product1", "product2", "product3"));


        submissionPublisher.submit(order);

        order = new Order();
        order.setId(2l);
        order.setOrderDate(new Date());
        order.setProducts(List.of("product1", "product2", "product3"));

        order.setTotal(BigDecimal.ZERO);

        submissionPublisher.submit(order);

        submissionPublisher.close();

        System.out.println("Waiting for processing.......");
        System.in.read();


    }


}
