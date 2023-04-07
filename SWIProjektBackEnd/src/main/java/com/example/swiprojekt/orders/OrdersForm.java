package com.example.swiprojekt.orders;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OrdersForm {
    private String subject;
    private double price;
    private LocalDate date;
}
