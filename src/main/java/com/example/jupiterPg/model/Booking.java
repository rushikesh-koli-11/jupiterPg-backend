package com.example.jupiterPg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
@Data
public class Booking {
    @Id
    private String id;
    private String pgId;
    private String bedId;
    private String userName;
    private String mobile;
    private String status; // PENDING, APPROVED, REJECTED
}

