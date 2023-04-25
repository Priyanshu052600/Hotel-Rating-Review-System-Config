package com.Icwd.user.service.UserService.repositories;

import com.Icwd.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

    //if we want to define any custom method we can write here else some methods to get the data like findbyid and other stuff will get implemented.
}
