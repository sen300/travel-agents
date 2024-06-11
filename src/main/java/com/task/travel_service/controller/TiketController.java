package com.task.travel_service.controller;

import com.task.travel_service.dto.ResponseSuccess;
import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.entity.TiketEntity;
import com.task.travel_service.entity.TravelEntity;
import com.task.travel_service.exception.DataNotFoundException;
import com.task.travel_service.repository.PenumpangRepository;
import com.task.travel_service.repository.TiketRepository;
import com.task.travel_service.repository.TravelRepository;
import com.task.travel_service.dto.TiketRequestBody;
import com.task.travel_service.service.TiketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TiketController {
    @Autowired
    TiketRepository tiketRepository;
    @Autowired
    PenumpangRepository penumpangRepository;
    @Autowired
    TravelRepository travelRepository;

    @Autowired
    TiketService service;

    @GetMapping("/tiket")
    public ResponseEntity<List<TiketEntity>> getAllTiket() throws DataNotFoundException {
        return ResponseEntity.ok(service.getAllTiket());
    }

    @GetMapping("/tiket/{id}")
    public ResponseEntity<TiketEntity> getTiketById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.ok(service.getTiket(id));
    }

    @PostMapping(value = "/tiket/add")
    public ResponseEntity tambahTiket(@Valid @RequestBody TiketRequestBody tiketRequestBody) throws
        DataNotFoundException {
        return ResponseEntity.ok(service.saveTiket(tiketRequestBody));

    }

    @PutMapping(value = "/tiket/{id}")
    public ResponseEntity<TiketEntity> updateTiket(@Valid @RequestBody TiketRequestBody tiketRequestBody,
                                                @PathVariable("id") Long id) throws DataNotFoundException {
        return new ResponseEntity<>(service.updateTiket(tiketRequestBody, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/tiket/{id}")
    public ResponseEntity<ResponseSuccess> deleteTiket(@PathVariable("id") Long id) throws
            DataNotFoundException {
        return new ResponseEntity<>(service.deleteTiket(id), HttpStatus.ACCEPTED);
    }
}
