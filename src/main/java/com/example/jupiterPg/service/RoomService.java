package com.example.jupiterPg.service;

import com.example.jupiterPg.dto.BulkRoomCreateDto;
import com.example.jupiterPg.model.Room;
import com.example.jupiterPg.repository.RoomRepository;
import com.example.jupiterPg.repository.BedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final BedService bedService;
    private final BedRepository bedRepository;


    public Room add(Room room) {

        if (roomRepository.existsByPgIdAndRoomNumber(
                room.getPgId(), room.getRoomNumber())) {
            throw new RuntimeException(
                    "Room " + room.getRoomNumber() + " already exists in this PG"
            );
        }

        Room saved = roomRepository.save(room);
        bedService.createBedsForRoom(saved);
        return saved;
    }


    public Room update(String id, Room room) {

        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Optional<Room> duplicate = roomRepository
                .findByPgIdAndRoomNumber(existing.getPgId(), room.getRoomNumber());

        if (duplicate.isPresent() && !duplicate.get().getId().equals(id)) {
            throw new RuntimeException(
                    "Room " + room.getRoomNumber() + " already exists in this PG"
            );
        }

        room.setId(id);
        room.setPgId(existing.getPgId()); // PG must not change

        bedRepository.deleteByRoomId(id);

        Room updated = roomRepository.save(room);

        bedService.createBedsForRoom(updated);

        return updated;
    }


    public java.util.List<Room> getByPg(String pgId) {
        return roomRepository
                .findByPgIdOrderByFloorNumberAscRoomNumberAsc(pgId);
    }


    public void delete(String id) {
        bedRepository.deleteByRoomId(id);
        roomRepository.deleteById(id);
    }


    public void bulkCreate(BulkRoomCreateDto dto) {

        for (int i = dto.getStartRoom(); i <= dto.getEndRoom(); i++) {

            String roomNumber = String.valueOf(i);

            if (roomRepository.existsByPgIdAndRoomNumber(
                    dto.getPgId(), roomNumber)) {
                throw new RuntimeException(
                        "Room " + roomNumber + " already exists in this PG"
                );
            }

            Room room = new Room();
            room.setPgId(dto.getPgId());
            room.setFloorNumber(dto.getFloorNumber());
            room.setRoomNumber(roomNumber);
            room.setSharingTypes(dto.getSharingTypes());

            Room saved = roomRepository.save(room);
            bedService.createBedsForRoom(saved);
        }
    }
}
