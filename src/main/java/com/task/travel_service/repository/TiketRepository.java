package com.task.travel_service.repository;

import com.task.travel_service.entity.TiketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiketRepository extends JpaRepository<TiketEntity, Long> {
}
