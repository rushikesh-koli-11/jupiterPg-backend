package com.example.jupiterPg.service;

import com.cloudinary.Cloudinary;
import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.model.Resident;
import com.example.jupiterPg.repository.BedRepository;
import com.example.jupiterPg.repository.ResidentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResidentServiceTest {

    @Mock
    private ResidentRepository residentRepository;

    @Mock
    private BedRepository bedRepository;

    @Mock
    private Cloudinary cloudinary;

    @InjectMocks
    private ResidentService residentService;

    @Test
    void add_shouldOccupyBed() {
        Bed bed = new Bed();
        bed.setStatus("AVAILABLE");

        Resident resident = new Resident();
        resident.setBedId("bed1");

        when(bedRepository.findById("bed1"))
                .thenReturn(Optional.of(bed));
        when(residentRepository.save(any()))
                .thenReturn(resident);

        Resident saved = residentService.add(resident, null);

        assertEquals("ACTIVE", saved.getStatus());
        assertEquals("OCCUPIED", bed.getStatus());
    }
}
