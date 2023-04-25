package com.Icwd.user.service.UserService.services;
import com.Icwd.user.service.UserService.entities.User;
import java.util.List;
public interface UserService {

    //user operation

    //create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get Single user of given userId
    User getUser(String userId);
}
