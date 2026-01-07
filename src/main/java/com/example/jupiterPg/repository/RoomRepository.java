package com.example.jupiterPg.repository;

import com.example.jupiterPg.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends MongoRepository<Room, String> {
    // 🔒 Duplicate check
    boolean existsByPgIdAndRoomNumber(String pgId, String roomNumber);

    Optional<Room> findByPgIdAndRoomNumber(String pgId, String roomNumber);

    // 🔽 Sorted fetch
    List<Room> findByPgIdOrderByFloorNumberAscRoomNumberAsc(String pgId);
}
