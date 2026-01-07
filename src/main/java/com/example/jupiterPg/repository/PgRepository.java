package com.example.jupiterPg.repository;

import com.example.jupiterPg.model.Pg;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PgRepository extends MongoRepository<Pg, String> {
    List<Pg> findByAdminId(String adminId);
}

