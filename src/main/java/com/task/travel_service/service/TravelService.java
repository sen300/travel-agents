package com.task.travel_service.service;

import com.task.travel_service.dto.ResponseSuccess;
import com.task.travel_service.entity.TravelEntity;
import com.task.travel_service.exception.DataNotFoundException;
import com.task.travel_service.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelService {
    @Autowired
    private TravelRepository travelRepository;

    public List<TravelEntity> getAllTravel() {
        return travelRepository.findAll();
    }

    public TravelEntity getTravel(Long id) {
        Optional<TravelEntity> travelEntityOptional = this.travelRepository.findById(id);
        if (travelEntityOptional.isPresent()) {
            return travelEntityOptional.get();
        } else {
            throw new DataNotFoundException("Travel not found, id: "+ id);
        }
    }

    public TravelEntity saveTravel(TravelEntity travel) {
        String nomorDepanLama = travel.getNoTelp().substring(0,2);
        if (nomorDepanLama.equals("62")){
            String nomorDepan = "0";
            String noTelp = nomorDepan + travel.getNoTelp().substring(2);
            travel.setNoTelp(noTelp);
        }
        return travelRepository.save(travel);
    }

    public TravelEntity updateTravel(TravelEntity travel, Long id) {
        Optional<TravelEntity> travelEntityOptional = travelRepository.findById(id);

        if (travelEntityOptional.isPresent()) {
            travelEntityOptional.get().setNamaTravel(travel.getNamaTravel());
            travelEntityOptional.get().setNoPolisi(travel.getNoPolisi());
            travelEntityOptional.get().setNoTelp(travel.getNoTelp());
            travelEntityOptional.get().setJenisBus(travel.getJenisBus());
            travelEntityOptional.get().setAlamat(travel.getAlamat());

            return travelRepository.save(travelEntityOptional.get());
        } else {
            throw new DataNotFoundException("Travel not found, id: "+ id);
        }
    }

    public ResponseSuccess deleteTravel(Long id) {
        Optional<TravelEntity> travelEntityOptional = travelRepository.findById(id);
        if (!travelEntityOptional.isPresent()) {
            throw new DataNotFoundException("Travel not found, id: "+ id);
        }
        travelRepository.deleteById(id);
        return new ResponseSuccess("Success", "Data berhasil dihapus", 200);
    }
}
