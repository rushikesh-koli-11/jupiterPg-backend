package com.example.jupiterPg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "residents")
@Data
public class Resident {

    @Id
    private String id;

    private String pgId;
    private String roomId;
    private int floorNumber;
    private String bedId;

    private String name;
    private String mobile;
    private String email;

    private Double rent;
    private Double deposit;

    // Resident.java
    private String idProofUrl;


    private LocalDate checkInDate;          // REQUIRED
    private LocalDate expectedCheckOutDate; // OPTIONAL
    private LocalDate actualCheckOutDate;   // SET ON LEAVE

    private String status; // ACTIVE, CHECKED_OUT
}

