package com.task.travel_service.service;

import com.task.travel_service.dto.ResponseSuccess;
import com.task.travel_service.dto.TiketRequestBody;
import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.entity.TiketEntity;
import com.task.travel_service.entity.TravelEntity;
import com.task.travel_service.exception.DataNotFoundException;
import com.task.travel_service.repository.PenumpangRepository;
import com.task.travel_service.repository.TiketRepository;
import com.task.travel_service.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TiketService {
    @Autowired
    TiketRepository tiketRepository;
    @Autowired
    PenumpangRepository penumpangRepository;
    @Autowired
    TravelRepository travelRepository;

    public List<TiketEntity> getAllTiket() {
        return tiketRepository.findAll();
    }

    public TiketEntity getTiket(Long id) {
        Optional<TiketEntity> tiketEntityOptional = tiketRepository.findById(id);
        if (tiketEntityOptional.isPresent()) {
            return tiketEntityOptional.get();
        } else {
            throw new DataNotFoundException("Tiket not found, id: "+ id);
        }
    }

    public TiketEntity saveTiket(TiketRequestBody tiketRequestBody) {
        Optional<TravelEntity> travelEntityOptional = travelRepository.findById(tiketRequestBody.getIdTravel());
        if (travelEntityOptional.isEmpty()) {
            throw new DataNotFoundException("Travel not found, id: "+ tiketRequestBody.getIdTravel());
        }
        Optional<PenumpangEntity> penumpangEntityOptional = penumpangRepository.findById(tiketRequestBody.getIdPenumpang());
        if (penumpangEntityOptional.isEmpty()) {
            throw new DataNotFoundException("Penumpang not found, id: "+ tiketRequestBody.getIdPenumpang());
        }

        TravelEntity travel = travelEntityOptional.get();
        PenumpangEntity penumpang = penumpangEntityOptional.get();

        TiketEntity tiket = new TiketEntity();
        tiket.setPenumpang(penumpang);
        tiket.setTravel(travel);
        tiket.setJadwal(tiketRequestBody.getJadwal());

        return tiketRepository.save(tiket);
    }

    public TiketEntity updateTiket(TiketRequestBody tiketRequestBody, Long id) {
        Optional<TiketEntity> tiketEntityOptional = tiketRepository.findById(id);
        if (!tiketEntityOptional.isPresent()) {
            throw new DataNotFoundException("Tiket not found, id: "+ id);
        }

        Optional<TravelEntity> travelEntityOptional = travelRepository.findById(tiketRequestBody.getIdTravel());
        if (travelEntityOptional.isEmpty()) {
            throw new DataNotFoundException("Travel not found, id: "+ tiketRequestBody.getIdTravel());
        }
        Optional<PenumpangEntity> penumpangEntityOptional = penumpangRepository.findById(tiketRequestBody.getIdPenumpang());
        if (penumpangEntityOptional.isEmpty()) {
            throw new DataNotFoundException("Penumpang not found, id: "+ tiketRequestBody.getIdPenumpang());
        }

        TravelEntity travel = travelEntityOptional.get();
        PenumpangEntity penumpang = penumpangEntityOptional.get();

        TiketEntity tiket = tiketEntityOptional.get();
        tiket.setPenumpang(penumpang);
        tiket.setTravel(travel);
        tiket.setJadwal(tiketRequestBody.getJadwal());

        return tiketRepository.save(tiket);
    }

    public ResponseSuccess deleteTiket(Long id) {
        Optional<TiketEntity> tiketEntityOptional = tiketRepository.findById(id);
        if (!tiketEntityOptional.isPresent()) {
            throw new DataNotFoundException("Tiket not found, id: "+ id);
        }
        travelRepository.deleteById(id);
        return new ResponseSuccess("Success", "Data berhasil dihapus", 200);
    }

    public List<TiketEntity> getTiketByPenumpangId (Long id) {
        Optional<PenumpangEntity> penumpangEntityOptional = penumpangRepository.findById(id);
        if (penumpangEntityOptional.isEmpty()) {
            throw new DataNotFoundException("Penumpang not found, id: "+id);
        }

        Optional<List<TiketEntity>> optionalTiketEntityList =
                tiketRepository.findByPenumpang(penumpangEntityOptional.get());
        if (optionalTiketEntityList.isEmpty()) {
            throw new DataNotFoundException("Tiket not found, id: "+id);
        }

        return optionalTiketEntityList.get();

    }
}
