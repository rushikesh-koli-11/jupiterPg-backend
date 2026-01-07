package com.example.jupiterPg.controller;

import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.service.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beds")
@RequiredArgsConstructor
public class BedController {

    private final BedService bedService;

    @GetMapping
    public List<Bed> getAll() {
        return bedService.getAll();
    }

    @GetMapping("/room/{roomId}")
    public List<Bed> getByRoom(@PathVariable String roomId) {
        return bedService.getByRoom(roomId);
    }
}

