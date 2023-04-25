package com.Icwd.hotel.HotelService.repository;

import com.Icwd.hotel.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String > {
}
