package com.example.jupiterPg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "rooms")
@Data
public class Room {

    @Id
    private String id;

    private String pgId;

    private int floorNumber;

    private String roomNumber;

    private List<SharingType> sharingTypes;
}
