package com.example.jupiterPg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pgs")
@Data
public class Pg {

    @Id
    private String id;

    private String adminId;

    private String name;

    private String location;

    private Integer totalFloors;

    private List<String> images;
}
