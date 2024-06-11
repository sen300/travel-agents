package com.task.travel_service.controller;

import com.task.travel_service.dto.ResponseSuccess;
import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.exception.DataNotFoundException;
import com.task.travel_service.service.PenumpangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class PenumpangController {
    @Autowired
    PenumpangService service;

    @GetMapping("/penumpang")
    public ResponseEntity<List<PenumpangEntity>> getAllPenumpang() throws DataNotFoundException {
        return ResponseEntity.ok(service.getAllPenumpang());
    }

    @GetMapping("/penumpang/{id}")
    public ResponseEntity<PenumpangEntity> getPenumpangById(@PathVariable("id") Long id) throws
            DataNotFoundException {
        return ResponseEntity.ok(service.getPenumpang(id));
    }

    @PostMapping(value = "/penumpang/add")
    public ResponseEntity<PenumpangEntity> tambahPenumpang(@Valid @RequestBody PenumpangEntity penumpang) throws
            DataNotFoundException {
        return ResponseEntity.ok(service.savePenumpang(penumpang));
    }

    @PutMapping(value = "/penumpang/{id}")
    public ResponseEntity<PenumpangEntity> updatePenumpang(@Valid @RequestBody PenumpangEntity penumpang,
                                                           @PathVariable("id") Long id) throws
            DataNotFoundException {
        return new ResponseEntity<>(service.updatePenumpang(penumpang, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/penumpang/{id}")
    public ResponseEntity<ResponseSuccess> deletePenumpang(@PathVariable("id") Long id)  throws
            DataNotFoundException {
        return ResponseEntity.ok(service.deletePenumpang(id));
    }

}
