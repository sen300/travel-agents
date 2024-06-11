package com.task.travel_service.service;

import com.task.travel_service.dto.ResponseSuccess;
import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.exception.DataNotFoundException;
import com.task.travel_service.repository.PenumpangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PenumpangService {

    @Autowired
    private PenumpangRepository penumpangRepository;

    public List<PenumpangEntity> getAllPenumpang() {
        return penumpangRepository.findAll();
    }

    public PenumpangEntity getPenumpang(Long id) {
        Optional<PenumpangEntity> penumpangEntityOptional = this.penumpangRepository.findById(id);
        if (penumpangEntityOptional.isPresent()) {
            return penumpangEntityOptional.get();
        } else {
            throw new DataNotFoundException("Penumpang not found, id:"+ id);
        }
    }

    public PenumpangEntity savePenumpang(PenumpangEntity penumpang) {
        return penumpangRepository.save(penumpang);
    }

    public PenumpangEntity updatePenumpang(PenumpangEntity penumpang, Long id) {
        Optional<PenumpangEntity> penumpangEntityOptionalData = penumpangRepository.findById(id);

        if (penumpangEntityOptionalData.isPresent()) {
            penumpangEntityOptionalData.get().setIdPenumpang(id);
            penumpangEntityOptionalData.get().setNama(penumpang.getNama());
            penumpangEntityOptionalData.get().setNoTelp(penumpang.getNoTelp());

            return penumpangRepository.save(penumpangEntityOptionalData.get());
        } else {
            throw new DataNotFoundException("Penumpang not found, id:"+ id);
        }
    }

    public ResponseSuccess deletePenumpang(Long id) {
        penumpangRepository.deleteById(id);
        return new ResponseSuccess("Success", "Data berhasil dihapus", 200);
    }
}
