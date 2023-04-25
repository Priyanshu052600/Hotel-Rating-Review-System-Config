package com.Icwd.hotel.HotelService.controllers;

import com.Icwd.hotel.HotelService.entities.Hotel;
import com.Icwd.hotel.HotelService.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class hotelController {

    private HotelServices hotelServices;
    @Autowired
    public hotelController(HotelServices theHotelServices){
        hotelServices=theHotelServices;
    }
    @PostMapping("/save")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelServices.create(hotel));
    }

    @GetMapping("/search/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        return ResponseEntity.status(HttpStatus.OK).body(hotelServices.get(hotelId));
    }

    @GetMapping("search")
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.status(HttpStatus.OK).body(hotelServices.getAll());
    }
}
