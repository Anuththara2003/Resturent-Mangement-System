package com.assignment.resturentmanagementsystem.Dto;


import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTo {
    private int orderId;
    private String Type;
    private int FeedbackID;
    private int CustomerID;
    private int ItemId;
    private double Price;
    private int Quantity;



}
