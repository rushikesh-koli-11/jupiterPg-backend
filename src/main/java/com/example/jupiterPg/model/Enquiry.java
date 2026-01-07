package com.example.jupiterPg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "enquiries")
@Data
public class Enquiry {

    @Id
    private String id;

    private String pgId;
    private String name;
    private String mobile;
    private String message;

    private String status = "NEW"; // NEW, CONTACTED, CLOSED

    private LocalDateTime createdAt = LocalDateTime.now();
}


