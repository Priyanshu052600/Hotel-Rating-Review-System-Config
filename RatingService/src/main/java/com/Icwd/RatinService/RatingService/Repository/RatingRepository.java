package com.Icwd.RatinService.RatingService.Repository;

import com.Icwd.RatinService.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,String> {

    //we have to create the custom finder method for findbyhotelId and findByUserId

    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);
}
