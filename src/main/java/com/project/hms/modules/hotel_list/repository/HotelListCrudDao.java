package com.project.hms.modules.hotel_list.repository;

import com.project.hms.modules.hotel_list.entity.HotelEntity;
import org.springframework.data.repository.CrudRepository;

public interface HotelListCrudDao extends CrudRepository<HotelEntity, Integer> {
}
