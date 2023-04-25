package com.Icwd.user.service.UserService.controller;

import com.Icwd.user.service.UserService.entities.User;
import com.Icwd.user.service.UserService.repositories.UserRepository;
import com.Icwd.user.service.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/users")
public class UserController {
    public UserService userService;
    //int retryCount=1;
    @Autowired
    public UserController(UserService theUserService) {
        userService = theUserService;
    }

    //create
    @PostMapping("/save")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
    @GetMapping("/search/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
    //@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        //System.out.println("retry is executed because service is down : "+retryCount);
        //retryCount++;
        User user=userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    //creatign fall back method for circuit breaker
    public ResponseEntity<User> ratingHotelFallBack(String userId,Exception e){
        System.out.println("Fallback is executed because service is down : "+e.getMessage());
        User user=User.builder().email("pg012600@gmail.com").name("Priyanshu").about("This user is created as dummy user because service is down").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser=userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
