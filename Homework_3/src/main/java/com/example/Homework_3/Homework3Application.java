package com.example.Homework_3;

import com.example.Homework_3.domain.Client;
import com.example.Homework_3.domain.DiscountType;
import com.example.Homework_3.domain.Order;
import com.example.Homework_3.service.ClientService;
import com.example.Homework_3.service.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Homework3Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework3Application.class, args);
    }

    @Bean
    public CommandLineRunner testOrders(OrderService orderService, ClientService clientService) {
        return args -> {
            Client loyalClient = clientService.saveClient(new Client(null, "Alice", "Smith", "alice@example.com", true));
            Client normalClient = clientService.saveClient(new Client(null, "Bob", "Johnson", "bob@example.com", false));

            Order order1 = new Order(null, 1200f, 0f, loyalClient.getId(), DiscountType.DEFAULT);
            Order order2 = new Order(null, 800f, 0f, loyalClient.getId(), DiscountType.DEFAULT);
            Order order3 = new Order(null, 1200f, 0f, normalClient.getId(), DiscountType.DEFAULT);
            Order order4 = new Order(null, 500f, 0f, normalClient.getId(), DiscountType.DEFAULT);

            System.out.println(orderService.createOrder(order1));
            System.out.println(orderService.createOrder(order2));
            System.out.println(orderService.createOrder(order3));
            System.out.println(orderService.createOrder(order4));
        };
    }
}
