package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFound;
import com.lcwd.user.service.external.Services.HotelService;
import com.lcwd.user.service.external.Services.RatingService;
import com.lcwd.user.service.repository.Userrepository;
import com.lcwd.user.service.services.Userservices;
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
public class UserServiceImpl implements Userservices {
    @Autowired
    Userrepository userrepository;

    @Autowired
    HotelService hotelService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {

        return userrepository.save(user);

    }

    @Override
    public List<User> getAllUser() {

        return userrepository.findAll();
    }

    @Override
    public User getUserById(String userId) throws ResourceNotFound {
        User user = userrepository.findById(userId).orElseThrow(() -> new ResourceNotFound("The user by id " + userId + " not found"));
        //http://localhost:8083/ratings/users/1

     //   Rating[] listRating = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
       //List<Rating>listOfRating=  Arrays.stream(listRating).toList();
       List<Rating> listOfRating = ratingService.getRatingByUserId(userId);

        List<Rating> ratingOfUser =  listOfRating.stream().map(rating -> {
            //http://localhost:8082/hotels/104
         // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
          //  Hotel hotelAsPerRating = forEntity.getBody();
            Hotel hotelAsPerRating = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotelAsPerRating);
            return rating;
        }).collect(Collectors.toList());

        user.setRating(listOfRating);
        return user;
    }

    @Override
    public void deleteUser(String userId) {
        List<Rating> listOfRatings = ratingService.getRating(userId);
        listOfRatings.stream().filter(rating -> {
            if (rating.getUserId().equals(userId)){
                ratingService.deleteRating(userId);
            }
            return true;
        });
//        ratingService.deleteRating(userId);
       userrepository.deleteById(userId);
    }
}
