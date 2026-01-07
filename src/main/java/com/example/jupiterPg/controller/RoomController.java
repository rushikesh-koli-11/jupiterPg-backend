package com.example.jupiterPg.controller;

import com.example.jupiterPg.dto.BulkRoomCreateDto;
import com.example.jupiterPg.model.Room;
import com.example.jupiterPg.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public Room add(@RequestBody Room room) {
        return roomService.add(room);
    }

    @GetMapping("/pg/{pgId}")
    public List<Room> getByPg(@PathVariable String pgId) {
        return roomService.getByPg(pgId);
    }

    @PutMapping("/{id}")
    public Room update(@PathVariable String id, @RequestBody Room room) {
        return roomService.update(id, room);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        roomService.delete(id);
    }

    @PostMapping("/bulk")
    public void bulkCreate(@RequestBody BulkRoomCreateDto dto) {
        roomService.bulkCreate(dto);
    }

}

