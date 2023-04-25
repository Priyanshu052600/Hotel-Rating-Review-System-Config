package com.Icwd.user.service.UserService.external.service;

import com.Icwd.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")

public interface HotelService {

    @GetMapping("/hotels/search/{hotelId}")
    Hotel gethotel(@PathVariable String hotelId);
}
