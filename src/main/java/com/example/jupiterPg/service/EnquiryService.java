package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Enquiry;
import com.example.jupiterPg.repository.EnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnquiryService {
    private final EnquiryRepository enquiryRepository;

    public List<Enquiry> getAll() {
        return enquiryRepository.findAll();
    }
}
