package com.example.jupiterPg.controller;

import com.example.jupiterPg.model.Enquiry;
import com.example.jupiterPg.repository.EnquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enquiries")
@RequiredArgsConstructor
public class EnquiryController {

    private final EnquiryRepository enquiryRepository;


    @PostMapping
    public Enquiry submitEnquiry(@RequestBody Enquiry enquiry) {
        return enquiryRepository.save(enquiry);
    }


    @GetMapping
    public List<Enquiry> getAll() {
        return enquiryRepository.findAllByOrderByCreatedAtDesc();
    }


    @PutMapping("/{id}/status")
    public Enquiry updateStatus(
            @PathVariable String id,
            @RequestParam String status
    ) {
        Enquiry enquiry = enquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));

        enquiry.setStatus(status);
        return enquiryRepository.save(enquiry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        enquiryRepository.deleteById(id);
    }


}
