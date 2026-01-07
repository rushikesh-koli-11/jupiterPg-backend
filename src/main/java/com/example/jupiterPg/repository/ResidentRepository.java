package com.example.jupiterPg.repository;

import com.example.jupiterPg.model.Resident;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResidentRepository extends MongoRepository<Resident, String> {

}
