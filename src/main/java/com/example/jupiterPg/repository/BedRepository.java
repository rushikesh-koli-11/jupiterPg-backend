package com.example.jupiterPg.repository;

import com.example.jupiterPg.model.Bed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BedRepository extends MongoRepository<Bed, String> {

    List<Bed> findByRoomId(String roomId);

    List<Bed> findByStatus(String status);

    long countByStatus(String status);

    void deleteByRoomId(String roomId);

}

