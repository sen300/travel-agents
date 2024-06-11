package com.task.travel_service.controller;

import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.entity.TiketEntity;
import com.task.travel_service.entity.TravelEntity;
import com.task.travel_service.repository.PenumpangRepository;
import com.task.travel_service.repository.TiketRepository;
import com.task.travel_service.repository.TravelRepository;
import com.task.travel_service.dto.TiketRequestBody;
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

    @GetMapping("/tiket")
    public ResponseEntity<List<TiketEntity>> GetAllTravel(){
        try {
            List<TiketEntity> tiketEntityList = tiketRepository.findAll();
            return new ResponseEntity<>(tiketEntityList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/tiket/{id}")
    public ResponseEntity<List<TiketEntity>> GetTravelById(@PathVariable("id") Long id) {
        try {
            Optional<TiketEntity> tiketEntityOptional = tiketRepository.findById(id);
            if (tiketEntityOptional.isPresent()) {
                return new ResponseEntity(tiketEntityOptional, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/tiket/add")
    public ResponseEntity TambahTiket(@Valid @RequestBody TiketRequestBody tiketRequestBody) {
        try {
            Optional<TravelEntity> travelEntityOptional = travelRepository.findById(tiketRequestBody.getIdTravel());
            if (travelEntityOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<PenumpangEntity> penumpangEntityOptional = penumpangRepository.findById(tiketRequestBody.getIdPenumpang());
            if (penumpangEntityOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            TravelEntity travel = travelEntityOptional.get();
            PenumpangEntity penumpang = penumpangEntityOptional.get();

            TiketEntity tiket = new TiketEntity();
            tiket.setPenumpang(penumpang);
            tiket.setTravel(travel);
            tiket.setJadwal(tiketRequestBody.getJadwal());

            tiketRepository.save(tiket);

            HashMap<String,String> result = new HashMap<>();
            result.put("Status", "200");
            result.put("Description", "Success");
            result.put("Message", "Data berhasil ditambah");

            return new ResponseEntity<HashMap>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/tiket/{id}")
    public ResponseEntity<HashMap> UpdateDoctor(@Valid @RequestBody TiketRequestBody tiketRequestBody,
                                                @PathVariable("id") Long id) {
        try {
            Optional<TiketEntity> tiketEntityOptional = tiketRepository.findById(id);
            if (!tiketEntityOptional.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Optional<TravelEntity> travelEntityOptional = travelRepository.findById(tiketRequestBody.getIdTravel());
            if (travelEntityOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<PenumpangEntity> penumpangEntityOptional = penumpangRepository.findById(tiketRequestBody.getIdPenumpang());
            if (penumpangEntityOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            TravelEntity travel = travelEntityOptional.get();
            PenumpangEntity penumpang = penumpangEntityOptional.get();
            System.out.println("test1");

            tiketEntityOptional.get().setId(id);
            tiketEntityOptional.get().setTravel(travel);
            tiketEntityOptional.get().setPenumpang(penumpang);
            tiketEntityOptional.get().setJadwal(tiketRequestBody.getJadwal());

            tiketRepository.save(tiketEntityOptional.get());

            HashMap<String,String> result = new HashMap<>();
            result.put("Status", "200");
            result.put("Description", "Success");
            result.put("Message", "Data berhasil diedit");

            return new ResponseEntity<HashMap>(result, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/tiket/{id}")
    public ResponseEntity<HashMap> deleteTravel(@PathVariable("id") Long id) {
        try {
            tiketRepository.deleteById(id);

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
