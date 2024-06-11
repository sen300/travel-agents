package com.task.travel_service.controller;

import com.task.travel_service.entity.TravelEntity;
import com.task.travel_service.repository.TravelRepository;
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
public class TravelController {
    @Autowired
    TravelRepository travelRepository;

    @GetMapping("/travel")
    public ResponseEntity<List<TravelEntity>> GetAllTravel(){
        try {
            List<TravelEntity> travelList = travelRepository.findAll();
            return new ResponseEntity<>(travelList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/travel/{id}")
    public ResponseEntity<List<TravelEntity>> GetTravelById(@PathVariable("id") Long id) {
        try {
            Optional<TravelEntity> travelEntityOptional = this.travelRepository.findById(id);
            if (travelEntityOptional.isPresent()) {
                return new ResponseEntity(travelEntityOptional, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/travel/add")
    public ResponseEntity TambahTravel(@Valid @RequestBody TravelEntity travel) {
        try {
            travelRepository.save(travel);

            HashMap<String,String> result = new HashMap<>();
            result.put("Status", "200");
            result.put("Description", "Success");
            result.put("Message", "Data berhasil ditambah");

            return new ResponseEntity<HashMap>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/travel/{id}")
    public ResponseEntity<HashMap> UpdateDoctor(@Valid @RequestBody TravelEntity travel,
                                               @PathVariable("id") Long id) {
        try {
            Optional<TravelEntity> travelEntityOptionalData = travelRepository.findById(id);

            if (travelEntityOptionalData.isPresent()) {
                travelEntityOptionalData.get().setId(id);
                travelEntityOptionalData.get().setNamaTravel(travel.getNamaTravel());
                travelEntityOptionalData.get().setAlamat(travel.getAlamat());
                travelEntityOptionalData.get().setNoTelp(travel.getNoTelp());
                travelEntityOptionalData.get().setJenisBus(travel.getJenisBus());
                travelEntityOptionalData.get().setNoPolisi(travel.getNoPolisi());
                travelRepository.save(travelEntityOptionalData.get());

                HashMap<String,String> result = new HashMap<>();
                result.put("Status", "200");
                result.put("Description", "Success");
                result.put("Message", "Data berhasil diedit");

                return new ResponseEntity<HashMap>(result, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/travel/{id}")
    public ResponseEntity<HashMap> deleteTravel(@PathVariable("id") Long id) {
        try {
            travelRepository.deleteById(id);

            HashMap<String,String> result = new HashMap<>();
            result.put("Status", "200");
            result.put("Description", "Success");
            result.put("Message", "Data berhasil dihapus");
            return new ResponseEntity<HashMap>((result), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
