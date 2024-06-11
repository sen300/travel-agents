package com.task.travel_service.repository;

import com.task.travel_service.entity.PenumpangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenumpangRepository extends JpaRepository<PenumpangEntity, Long> {
}
