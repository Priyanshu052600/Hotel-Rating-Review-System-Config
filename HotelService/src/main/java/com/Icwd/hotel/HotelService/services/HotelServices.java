package com.Icwd.hotel.HotelService.services;

import com.Icwd.hotel.HotelService.entities.Hotel;
import java.util.List;

public interface HotelServices {

    //create
    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);
}
