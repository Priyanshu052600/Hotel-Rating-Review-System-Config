package com.Icwd.RatinService.RatingService.services.impl;

import com.Icwd.RatinService.RatingService.Repository.RatingRepository;
import com.Icwd.RatinService.RatingService.entities.Rating;
import com.Icwd.RatinService.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository theRatingRepository){
        ratingRepository=theRatingRepository;
    }

    @Override
    public Rating create(Rating rating) {
        String ratingId= UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getRating() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
