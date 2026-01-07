package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.model.Room;
import com.example.jupiterPg.model.SharingType;
import com.example.jupiterPg.repository.BedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BedServiceTest {

    @Mock
    private BedRepository bedRepository;

    @InjectMocks
    private BedService bedService;

    @Test
    void createBedsForRoom_createsCorrectBeds() {
        Room room = new Room();
        room.setId("room1");
        room.setRoomNumber("101");

        SharingType st = new SharingType();
        st.setType("DOUBLE");
        st.setCapacity(2);

        room.setSharingTypes(List.of(st));

        bedService.createBedsForRoom(room);

        verify(bedRepository, times(2)).save(any(Bed.class));
    }
}
