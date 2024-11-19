package com.assignment.resturentmanagementsystem.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersViewDto {
    private int orderId;
    private String itemId;
    private int quantity;
    private double price;
    private String Type;
}
