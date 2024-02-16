package com.lcwd.hotel.service;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.exception.ResourceNotFoundException;

import java.util.List;

public interface HotelService {

    //Create
    Hotel saveHotel(Hotel hotel);

    //GetAllHotels
    List<Hotel> getAllHotels();

    //GetHotelById
    Hotel getHotelById(String hotelId) throws ResourceNotFoundException;
}
