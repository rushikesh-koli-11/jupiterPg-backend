package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Enquiry;
import com.example.jupiterPg.repository.EnquiryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnquiryServiceTest {

    @Mock
    private EnquiryRepository enquiryRepository;

    @InjectMocks
    private EnquiryService enquiryService;

    @Test
    void getAll_returnsEnquiries() {
        when(enquiryRepository.findAll())
                .thenReturn(List.of(new Enquiry(), new Enquiry()));

        assertEquals(2, enquiryService.getAll().size());
    }
}
