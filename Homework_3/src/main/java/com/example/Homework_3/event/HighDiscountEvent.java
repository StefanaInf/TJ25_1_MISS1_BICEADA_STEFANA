package com.example.Homework_3.event;

import com.example.Homework_3.domain.Order;
import org.springframework.context.ApplicationEvent;

public class HighDiscountEvent extends ApplicationEvent {

    private final Order order;
    private final float discountAmount;

    public HighDiscountEvent(Object source, Order order, float discountAmount) {
        super(source);
        this.order = order;
        this.discountAmount = discountAmount;
    }

    public Order getOrder() {
        return order;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }
}
