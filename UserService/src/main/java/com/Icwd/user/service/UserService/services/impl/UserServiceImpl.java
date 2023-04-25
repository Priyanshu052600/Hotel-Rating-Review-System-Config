package com.Icwd.user.service.UserService.services.impl;
import com.Icwd.user.service.UserService.entities.Hotel;
import com.Icwd.user.service.UserService.entities.Rating;
import com.Icwd.user.service.UserService.entities.User;
import com.Icwd.user.service.UserService.exceptions.ResourceNotFoundException;
import com.Icwd.user.service.UserService.external.service.HotelService;
import com.Icwd.user.service.UserService.repositories.UserRepository;
import com.Icwd.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private HotelService hotelService;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }
    @Override
    public User saveUser(User user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implementing rating service call
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get the user details form userID
        User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : "+userId ));
        //fetch the rating data to get the rating by userId
        // we need to call the rating api using https://localhost:8083/ratings/user/{userId}
        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);
        List<Rating> ratings= Arrays.stream(ratingsOfUser).toList();
        //
        List<Rating> ratingList=ratings.stream().map(rating ->{
            //api call to hotel service to get hotel "http://localhost:8083/hotels/search/{hotelId}"
            //ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/search/"+rating.getHotelId(),Hotel.class);
            //Hotel hotel=forEntity.getBody();
            //logger.info("Response status code : {}",forEntity.getStatusCode());
            Hotel hotel=hotelService.gethotel(rating.getHotelId());

            //set the hotel rating
            rating.setHotel(hotel);

            //return the hotel to rating

            //return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return user;
    }
}
