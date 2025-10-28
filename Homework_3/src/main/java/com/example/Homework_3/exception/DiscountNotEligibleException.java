package com.example.Homework_3.exception;

public class DiscountNotEligibleException extends RuntimeException {
    public DiscountNotEligibleException(String message) {
        super(message);
    }
}
