package com.task.travel_service.repository;

import com.task.travel_service.entity.PenumpangEntity;
import com.task.travel_service.entity.TiketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TiketRepository extends JpaRepository<TiketEntity, Long> {
    Optional<List<TiketEntity>> findByPenumpang(PenumpangEntity penumpang);
}
