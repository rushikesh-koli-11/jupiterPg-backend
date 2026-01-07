package com.example.jupiterPg.service;

import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.model.Room;
import com.example.jupiterPg.model.SharingType;
import com.example.jupiterPg.repository.BedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BedService {

    private final BedRepository bedRepository;

    public void createBedsForRoom(Room room) {
        for (SharingType st : room.getSharingTypes()) {
            int capacity = st.getCapacity(); // 1,2,3
            for (int i = 1; i <= capacity; i++) {
                Bed bed = new Bed();
                bed.setRoomId(room.getId());
                bed.setSharingType(st.getType());
                bed.setBedNumber(room.getRoomNumber() + "-" + st.getType().charAt(0) + i);
                bed.setStatus("AVAILABLE");
                bedRepository.save(bed);
            }
        }
    }

    public List<Bed> getAll() {
        return bedRepository.findAll();
    }


    public List<Bed> getByRoom(String roomId) {
        return bedRepository.findByRoomId(roomId);
    }
}
