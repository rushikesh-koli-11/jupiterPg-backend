package com.example.jupiterPg.dto;

import com.example.jupiterPg.model.SharingType;
import lombok.Data;

import java.util.List;

@Data
public class BulkRoomCreateDto {
    private String pgId;
    private int floorNumber;
    private int startRoom;
    private int endRoom;
    private List<SharingType> sharingTypes;
}

