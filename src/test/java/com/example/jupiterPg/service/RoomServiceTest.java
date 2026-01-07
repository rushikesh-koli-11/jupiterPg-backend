package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Room;
import com.example.jupiterPg.repository.BedRepository;
import com.example.jupiterPg.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private BedService bedService;

    @Mock
    private BedRepository bedRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void add_shouldSaveRoomAndCreateBeds() {
        Room room = new Room();
        room.setPgId("pg1");
        room.setRoomNumber("101");

        when(roomRepository.existsByPgIdAndRoomNumber(any(), any()))
                .thenReturn(false);
        when(roomRepository.save(any()))
                .thenReturn(room);

        roomService.add(room);

        verify(bedService).createBedsForRoom(room);
    }
}
