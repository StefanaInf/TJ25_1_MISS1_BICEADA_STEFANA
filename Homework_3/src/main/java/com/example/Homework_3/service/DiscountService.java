package com.example.Homework_3.service;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.domain.Order;

public interface DiscountService {
    Order applyDiscount(Order order, Client client);
}
