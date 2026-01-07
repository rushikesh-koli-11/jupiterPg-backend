package com.example.jupiterPg.service;

import com.example.jupiterPg.dto.DashboardStatsDto;
import com.example.jupiterPg.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock private PgRepository pgRepository;
    @Mock private RoomRepository roomRepository;
    @Mock private BedRepository bedRepository;
    @Mock private BookingRepository bookingRepository;
    @Mock private EnquiryRepository enquiryRepository;
    @Mock private ResidentRepository residentRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @Test
    void getDashboardStats_returnsCorrectStats() {
        when(pgRepository.count()).thenReturn(1L);
        when(roomRepository.count()).thenReturn(2L);
        when(bedRepository.count()).thenReturn(10L);
        when(bedRepository.countByStatus("OCCUPIED")).thenReturn(4L);
        when(bedRepository.countByStatus("AVAILABLE")).thenReturn(6L);
        when(bookingRepository.count()).thenReturn(3L);
        when(enquiryRepository.count()).thenReturn(5L);
        when(residentRepository.findAll()).thenReturn(List.of());

        DashboardStatsDto dto = dashboardService.getDashboardStats();

        assertEquals(10, dto.getTotalBeds());
        assertEquals(4, dto.getOccupiedBeds());
        assertEquals(6, dto.getAvailableBeds());
    }
}
