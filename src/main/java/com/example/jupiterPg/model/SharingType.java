package com.example.jupiterPg.model;

import lombok.Data;

@Data
public class SharingType {

    private String type;      // SINGLE, DOUBLE, TRIPLE
    private int capacity;     // 1, 2, 3
    private double rent;      // rent per bed
}
