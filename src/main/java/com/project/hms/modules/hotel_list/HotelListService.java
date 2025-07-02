package com.project.hms.modules.hotel_list;

import com.project.hms.modules.hotel_list.dto.HotelResponse;
import com.project.hms.modules.hotel_list.entity.HotelEntity;
import com.project.hms.modules.hotel_list.repository.HotelListCrudDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HotelListService {

    private final HotelListCrudDao hotelListCrudDao;

    public HotelListService(HotelListCrudDao hotelListCrudDao) {
        this.hotelListCrudDao = hotelListCrudDao;
    }

    public HotelResponse getHotel(Integer hotelId) {
        Optional<HotelEntity> hotelEntity = hotelListCrudDao.findById(hotelId);
        return hotelEntity.map(this::mapToResponse).orElseGet(HotelResponse::new);
    }

    public List<HotelResponse> getAllHotels() {
        List<HotelResponse> resultList = new ArrayList<>();
        hotelListCrudDao.findAll().forEach(e -> resultList.add(mapToResponse(e)));
        return resultList;
    }

    private HotelResponse mapToResponse(HotelEntity hotelEntity) {
        return Objects.nonNull(hotelEntity) ? new HotelResponse(hotelEntity.getId(), hotelEntity.getName(),
                hotelEntity.getCity(), hotelEntity.getCountry(), hotelEntity.getAddress(), hotelEntity.getPrice()) : new HotelResponse();
    }
}
