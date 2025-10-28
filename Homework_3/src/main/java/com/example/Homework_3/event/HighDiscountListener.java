package com.example.Homework_3.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HighDiscountListener {

    private static final Logger logger = LoggerFactory.getLogger(HighDiscountListener.class);

    @EventListener
    public void handleHighDiscount(HighDiscountEvent event) {
        logger.warn("High discount applied! Order ID: {}, Discount: {}",
                event.getOrder().getId(),
                event.getDiscountAmount());
    }
}
