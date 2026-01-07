package com.example.jupiterPg.controller;

import com.example.jupiterPg.model.Resident;
import com.example.jupiterPg.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/residents")
@RequiredArgsConstructor
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping(consumes = "multipart/form-data")
    public Resident add(
            @RequestPart("resident") Resident resident,
            @RequestPart(value = "idProof", required = false) MultipartFile idProof
    ) {
        return residentService.add(resident, idProof);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Resident update(
            @PathVariable String id,
            @RequestPart("resident") Resident resident,
            @RequestPart(value = "idProof", required = false) MultipartFile idProof
    ) {
        return residentService.update(id, resident, idProof);
    }


    @PutMapping("/{id}/checkout")
    public Resident checkout(
            @PathVariable String id,
            @RequestParam(required = false) String date
    ) {
        LocalDate checkoutDate = date != null
                ? LocalDate.parse(date)
                : LocalDate.now();

        return residentService.checkout(id, checkoutDate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        residentService.delete(id);
    }

    @GetMapping
    public List<Resident> getAll() {
        return residentService.getAll();
    }
}

