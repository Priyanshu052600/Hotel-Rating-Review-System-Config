package com.Icwd.RatinService.RatingService.services;

import com.Icwd.RatinService.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);

    //get all rating
    List<Rating> getRating();

    //get all by user
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);


}
