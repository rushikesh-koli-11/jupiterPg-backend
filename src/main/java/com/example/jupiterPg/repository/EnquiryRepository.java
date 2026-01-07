package com.example.jupiterPg.repository;

import com.example.jupiterPg.model.Enquiry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EnquiryRepository extends MongoRepository<Enquiry, String> {

    List<Enquiry> findAllByOrderByCreatedAtDesc();
}
