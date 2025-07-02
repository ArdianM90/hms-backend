package com.project.hms.modules.hotel_list;

import com.project.hms.modules.hotel_list.dto.HotelResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/hms/v1")
public class HotelListController {

    private final HotelListService hotelListService;

    public HotelListController(HotelListService hotelListService) {
        this.hotelListService = hotelListService;
    }

    @GetMapping("/hotel")
    public HotelResponse getHotel(@RequestParam Integer hotelId) {return hotelListService.getHotel(hotelId);
    }

    @GetMapping("/hotels")
    public List<HotelResponse> getAllHotels() {
        return hotelListService.getAllHotels();
    }
}
