package com.project.hms.modules.reservation.repository;

import com.project.hms.modules.reservation.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationCrudDao extends CrudRepository<ReservationEntity, Integer> {
}
