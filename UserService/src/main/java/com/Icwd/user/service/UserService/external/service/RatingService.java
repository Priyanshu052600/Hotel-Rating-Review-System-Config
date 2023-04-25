package com.Icwd.user.service.UserService.external.service;

import com.Icwd.user.service.UserService.entities.Rating;
import jakarta.ws.rs.POST;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    //get

    //post
    @PostMapping("/ratings/save")
    public Rating createRating(Rating rating);

    //put

}
