package com.lcwd.user.service.services;


import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface Userservices {

   //Create User
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //Get user by id
    User getUserById(String userId) throws ResourceNotFound;

    void deleteUser(String userId);
}
