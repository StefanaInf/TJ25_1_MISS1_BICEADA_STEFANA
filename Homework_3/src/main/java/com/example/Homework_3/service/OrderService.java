package com.example.Homework_3.service;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.domain.Order;
import com.example.Homework_3.repository.ClientRepository;
import com.example.Homework_3.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final DiscountService discountService;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public OrderService(DiscountService discountService,
                        OrderRepository orderRepository,
                        ClientRepository clientRepository) {
        this.discountService = discountService;
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        Long clientId = order.getClientId();
        if (clientId == null) {
            throw new IllegalArgumentException("Client ID must not be null");
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + clientId));

        Order discountedOrder = discountService.applyDiscount(order, client);

        discountedOrder.setClientId(clientId);

        Order savedOrder = orderRepository.save(discountedOrder);

        logger.info("Order created for {} {}. Total: {} , Final: {}",
                client.getFirstName(),
                client.getLastName(),
                savedOrder.getTotalAmount(),
                savedOrder.getFinalAmount());

        return savedOrder;
    }
}
