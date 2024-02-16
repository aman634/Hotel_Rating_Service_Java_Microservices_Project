package com.lcwd.rating.services;

import com.lcwd.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all rating
    List<Rating> getAllRating();

    //get rating by used id
    List<Rating> getRatingByUserID(String userId);

    //get rating by hotel id
    List<Rating> getRatingByHotelId(String hotelId);


    void deleteRatingByUserId(String userId);
}
