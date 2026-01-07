package com.example.jupiterPg.controller;

import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.model.Booking;
import com.example.jupiterPg.repository.BedRepository;
import com.example.jupiterPg.repository.BookingRepository;
import com.example.jupiterPg.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        booking.setStatus("PENDING");
        return bookingService.create(booking);
    }

    @PutMapping("/{id}/approve")
    public Booking approve(@PathVariable String id) {
        return bookingService.approve(id);
    }
}
