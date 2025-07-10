package com.project.hms.modules.hotel_list;

import com.project.hms.modules.hotel_list.dto.HotelResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hms/v1")
public class HotelListController {

    private final HotelListService hotelListService;

    public HotelListController(HotelListService hotelListService) {
        this.hotelListService = hotelListService;
    }

    @GetMapping("/hotel/{id}")
    public HotelResponse getHotel(@PathVariable Integer id) {
        return hotelListService.getHotel(id);
    }

    @GetMapping("/hotels")
    public List<HotelResponse> getAllHotels() {
        return hotelListService.getAllHotels();
    }
}
