package com.example.jupiterPg.controller;

import com.example.jupiterPg.model.Pg;
import com.example.jupiterPg.service.PgService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pgs")
@RequiredArgsConstructor
public class PgController {

    private final PgService pgService;

    /* ================= CREATE ================= */

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pg create(
            @RequestPart("pg") String pgJson,
            @RequestPart(value = "images", required = false) MultipartFile[] images
    ) throws Exception {

        Pg pg = new ObjectMapper().readValue(pgJson, Pg.class);
        return pgService.create(pg, images);
    }

    /* ================= UPDATE ================= */

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Pg update(
            @PathVariable String id,
            @RequestPart("pg") String pgJson,
            @RequestPart(value = "images", required = false) MultipartFile[] images
    ) throws Exception {

        Pg pg = new ObjectMapper().readValue(pgJson, Pg.class);
        return pgService.update(id, pg, images);
    }

    /* ================= READ ================= */

    @GetMapping
    public List<Pg> getAll() {
        return pgService.getAll();
    }

    /* ================= DELETE ================= */

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        pgService.delete(id);
    }
}
