package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.model.Booking;
import com.example.jupiterPg.repository.BedRepository;
import com.example.jupiterPg.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BedRepository bedRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void approve_shouldOccupyBed() {
        Booking booking = new Booking();
        booking.setId("b1");
        booking.setBedId("bed1");

        Bed bed = new Bed();
        bed.setStatus("AVAILABLE");

        when(bookingRepository.findById("b1"))
                .thenReturn(Optional.of(booking));
        when(bedRepository.findById("bed1"))
                .thenReturn(Optional.of(bed));

        bookingService.approve("b1");

        assertEquals("OCCUPIED", bed.getStatus());
        verify(bedRepository).save(bed);
    }
}
