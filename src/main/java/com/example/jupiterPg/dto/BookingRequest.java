package com.example.jupiterPg.dto;

import lombok.Data;

@Data
public class BookingRequest {

    private String pgId;
    private String bedId;
    private String userName;
    private String mobile;
}
