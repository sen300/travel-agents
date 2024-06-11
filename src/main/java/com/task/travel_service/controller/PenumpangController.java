package com.task.travel_service.controller;

import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.exception.ApiRequestException;
import com.task.travel_service.repository.PenumpangRepository;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
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
public class PenumpangController {
    @Autowired
    PenumpangRepository penumpangRepository;

    @GetMapping("/penumpang")
    public ResponseEntity<List<PenumpangEntity>> GetAllPenumpang(){
        try {
            List<PenumpangEntity> penumpangList = this.penumpangRepository.findAll();
            return new ResponseEntity<>(penumpangList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/penumpang/{id}")
    public ResponseEntity<List<PenumpangEntity>> GetPenumpangById(@PathVariable("id") Long id) {
        try {
            Optional<PenumpangEntity> penumpangEntityOptional = this.penumpangRepository.findById(id);
            if (penumpangEntityOptional.isPresent()) {
                return new ResponseEntity(penumpangEntityOptional, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/penumpang/add")
    public ResponseEntity TambahPenumpang(@Valid @RequestBody PenumpangEntity penumpang) {
        try {
            penumpangRepository.save(penumpang);

            HashMap<String,String> result = new HashMap<>();
            result.put("Status", "200");
            result.put("Description", "Success");
            result.put("Message", "Data berhasil ditambah");

            return new ResponseEntity<HashMap>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping(value = "/penumpang/{id}")
    public ResponseEntity<HashMap> UpdateDoctor(@Valid @RequestBody PenumpangEntity penumpang,
                                               @PathVariable("id") Long id) {
        try {
            Optional<PenumpangEntity> penumpangEntityOptionalData = penumpangRepository.findById(id);

            if (penumpangEntityOptionalData.isPresent()) {
                penumpangEntityOptionalData.get().setIdPenumpang(id);
                penumpangEntityOptionalData.get().setNama(penumpang.getNama());
                penumpangEntityOptionalData.get().setNoTelp(penumpang.getNoTelp());
                penumpangRepository.save(penumpangEntityOptionalData.get());

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

    @DeleteMapping("/penumpang/{id}")
    public ResponseEntity<HashMap> deletePenumpang(@PathVariable("id") Long id) {
        try {
            penumpangRepository.deleteById(id);

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
