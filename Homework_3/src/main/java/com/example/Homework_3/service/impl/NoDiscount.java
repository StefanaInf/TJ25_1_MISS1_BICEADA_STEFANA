package com.example.Homework_3.service.impl;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.domain.DiscountType;
import com.example.Homework_3.domain.Order;
import com.example.Homework_3.service.DiscountService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("default-discount")
public class NoDiscount implements DiscountService {

    @Override
    public Order applyDiscount(Order order, Client client) {

        order.setDiscountType(DiscountType.DEFAULT);
        order.setFinalAmount(order.getTotalAmount());

        return order;
    }
}