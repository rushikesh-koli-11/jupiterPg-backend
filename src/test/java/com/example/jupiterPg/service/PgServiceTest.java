package com.example.jupiterPg.service;

import com.cloudinary.Cloudinary;
import com.example.jupiterPg.model.Pg;
import com.example.jupiterPg.repository.PgRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PgServiceTest {

    @Mock
    private PgRepository pgRepository;

    @Mock
    private Cloudinary cloudinary;

    @InjectMocks
    private PgService pgService;

    @Test
    void getAll_returnsPgs() {
        when(pgRepository.findAll())
                .thenReturn(List.of(new Pg()));

        assertEquals(1, pgService.getAll().size());
    }
}
