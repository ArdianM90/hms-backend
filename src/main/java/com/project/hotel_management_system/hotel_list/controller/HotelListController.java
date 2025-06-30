package com.project.hotel_management_system.hotel_list.controller;

import com.project.hotel_management_system.hotel_list.HotelListService;
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
