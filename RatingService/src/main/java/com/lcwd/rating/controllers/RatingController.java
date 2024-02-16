package com.lcwd.rating.controllers;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {


    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        try {
            Rating rating1 = ratingService.createRating(rating);
            return ResponseEntity.ok(rating1);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        try {
            List<Rating> allRatings = ratingService.getAllRating();
            return ResponseEntity.ok(allRatings);
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable("userId") String userId){
        try {
            List<Rating> ratingByUserId = ratingService.getRatingByUserID(userId);
            return ResponseEntity.ok(ratingByUserId);
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
        try {
            List<Rating> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
            return ResponseEntity.ok(ratingByHotelId);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteRatingByUserId(@PathVariable String userId){
        ratingService.deleteRatingByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}
