package com.task.travel_service.controller;

import com.task.travel_service.dto.ResponseSuccess;
import com.task.travel_service.entity.TravelEntity;
import com.task.travel_service.exception.DataNotFoundException;
import com.task.travel_service.service.TravelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TravelController {
    @Autowired
    TravelService service;

    @GetMapping("/travel")
    public ResponseEntity<List<TravelEntity>> getAllTravel(){
        return ResponseEntity.ok(service.getAllTravel());
    }

    @GetMapping("/travel/{id}")
    public ResponseEntity<TravelEntity> getTravelById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.ok(service.getTravel(id));
    }

    @PostMapping(value = "/travel/add")
    public ResponseEntity<TravelEntity> tambahTravel(@Valid @RequestBody TravelEntity travel) throws DataNotFoundException {
        return ResponseEntity.ok(service.saveTravel(travel));
    }

    @PutMapping(value = "/travel/{id}")
    public ResponseEntity<TravelEntity> updateTravel(@Valid @RequestBody TravelEntity travel,
                                               @PathVariable("id") Long id)  throws DataNotFoundException{
        return new ResponseEntity<>(service.updateTravel(travel, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/travel/{id}")
    public ResponseEntity<ResponseSuccess> deleteTravel(@PathVariable("id") Long id)  throws DataNotFoundException{
        return ResponseEntity.ok(service.deleteTravel(id));
    }

}
