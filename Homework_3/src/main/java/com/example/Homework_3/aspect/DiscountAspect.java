package com.example.Homework_3.aspect;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.domain.Order;
import com.example.Homework_3.event.HighDiscountEvent;
import com.example.Homework_3.exception.DiscountNotEligibleException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DiscountAspect {

    private static final Logger logger = LoggerFactory.getLogger(DiscountAspect.class);

    @Value("${discount.HIGH_DISCOUNT_THRESHOLD}")
    private float HIGH_DISCOUNT_THRESHOLD;

    private final ApplicationEventPublisher eventPublisher;

    public DiscountAspect(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Around("execution(* com.example.Homework_3.service.DiscountService.applyDiscount(..))")
    public Object applyDiscountAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        Order order = (Order) args[0];
        Client client = (Client) args[1];

        if (client == null) {
            throw new DiscountNotEligibleException("Client does not exist.");
        }

        Order discountedOrder = (Order) joinPoint.proceed();

        float discountAmount = discountedOrder.getTotalAmount() - discountedOrder.getFinalAmount();

        logger.info("{} applied for client {} {}. Discount amount: {}",
                joinPoint.getSignature().getName(),
                client.getFirstName(),
                client.getLastName(),
                discountAmount);

        if (discountAmount > HIGH_DISCOUNT_THRESHOLD) {
            eventPublisher.publishEvent(new HighDiscountEvent(this, discountedOrder, discountAmount));
        }

        return discountedOrder;
    }
}
