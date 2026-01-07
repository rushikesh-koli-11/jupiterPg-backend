package com.example.jupiterPg.repository;

import com.example.jupiterPg.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {

    long countByStatus(String status);

    List<Booking> findByStatus(String status);

    List<Booking> findByPgId(String pgId);
}
