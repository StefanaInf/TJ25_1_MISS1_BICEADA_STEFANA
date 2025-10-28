package com.example.Homework_3.service.impl;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.domain.DiscountType;
import com.example.Homework_3.domain.Order;
import com.example.Homework_3.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("large-order")
public class LargeOrderDiscount implements DiscountService {

    @Value("${discount.HIGH_DISCOUNT_THRESHOLD}")
    private float HIGH_DISCOUNT_THRESHOLD;

    @Value("${discount.FIXED_DISCOUNT_AMOUNT}")
    private float FIXED_DISCOUNT_AMOUNT;

    Logger logger = LoggerFactory.getLogger(LargeOrderDiscount.class);

    @Override
    public Order applyDiscount(Order order, Client client) {
        float discountAmount = 0.0f;

        if (order.getTotalAmount() > HIGH_DISCOUNT_THRESHOLD) {
            discountAmount = FIXED_DISCOUNT_AMOUNT;
            order.setDiscountType(DiscountType.LARGE_VALUE_DISCOUNT);
        } else {
            order.setDiscountType(DiscountType.DEFAULT);
        }

        order.setFinalAmount(order.getTotalAmount() - discountAmount);

        return order;
    }
}
