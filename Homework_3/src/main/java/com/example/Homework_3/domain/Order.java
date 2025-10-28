package com.example.Homework_3.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float totalAmount;
    private float finalAmount;

    private Long clientId;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
}
