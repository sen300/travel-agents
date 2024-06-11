package com.task.travel_service.controller;

import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.entity.TiketEntity;
import com.task.travel_service.entity.TravelEntity;
import com.task.travel_service.repository.PenumpangRepository;
import com.task.travel_service.repository.TiketRepository;
import com.task.travel_service.repository.TravelRepository;
import com.task.travel_service.request.TiketRequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @PostMapping(value = "/tiket/add")
    public ResponseEntity TambahTiket(@RequestBody TiketRequestBody tiketRequestBody) {
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
}
