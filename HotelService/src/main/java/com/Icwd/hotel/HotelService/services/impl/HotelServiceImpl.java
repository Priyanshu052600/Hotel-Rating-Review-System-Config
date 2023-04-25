package com.Icwd.hotel.HotelService.services.impl;

import com.Icwd.hotel.HotelService.entities.Hotel;
import com.Icwd.hotel.HotelService.exceptions.ResourceNotFoundException;
import com.Icwd.hotel.HotelService.repository.HotelRepository;
import com.Icwd.hotel.HotelService.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelServices {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    public HotelServiceImpl(HotelRepository theHotelRepository){
        hotelRepository=theHotelRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with ID is not found in the server !! : "+id));
    }
}
