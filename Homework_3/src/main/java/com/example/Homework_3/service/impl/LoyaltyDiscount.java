package com.example.Homework_3.service.impl;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.domain.DiscountType;
import com.example.Homework_3.domain.Order;
import com.example.Homework_3.exception.DiscountNotEligibleException;
import com.example.Homework_3.service.DiscountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("loyalty")
public class LoyaltyDiscount implements DiscountService {

    @Value("${discount.LOYALTY_PERCENTAGE}")
    private float LOYALTY_PERCENTAGE;

    @Override
    public Order applyDiscount(Order order, Client client) {
        if (!client.isLoyal()) {
            throw new DiscountNotEligibleException(
                    "Client is not eligible for loyalty discount."
            );
        }

        float discountAmount = order.getTotalAmount() * LOYALTY_PERCENTAGE;
        order.setFinalAmount(order.getTotalAmount() - discountAmount);
        order.setDiscountType(DiscountType.LOYALTY_DISCOUNT);

        return order;
    }
}
