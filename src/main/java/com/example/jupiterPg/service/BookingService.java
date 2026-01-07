package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.model.Booking;
import com.example.jupiterPg.repository.BedRepository;
import com.example.jupiterPg.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BedRepository bedRepository;

    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking approve(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setStatus("APPROVED");

        Bed bed = bedRepository.findById(booking.getBedId()).orElseThrow();
        bed.setStatus("OCCUPIED");
        bedRepository.save(bed);

        return bookingRepository.save(booking);
    }
}

