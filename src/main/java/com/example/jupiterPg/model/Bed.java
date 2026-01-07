package com.example.jupiterPg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "beds")
@Data
public class Bed {

    @Id
    private String id;

    private String roomId;

    private String sharingType; // SINGLE / DOUBLE / TRIPLE

    private String bedNumber;

    private String status; // AVAILABLE / OCCUPIED
}
