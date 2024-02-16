package com.lcwd.user.service.external.Services;

import com.lcwd.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings")
    List<Rating> getAllRatings();

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRatingByUserId(String userId);

    @GetMapping("/ratings/users/{userId}")
    List<Rating> getRating(@PathVariable String userId);

    //create rating
    @PostMapping("/ratings")
    List<Rating> createRating(Rating rating);

    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, @RequestBody Rating rating);

    @DeleteMapping("/ratings/{userId}")
    Void deleteRating(@PathVariable String userId);

}
