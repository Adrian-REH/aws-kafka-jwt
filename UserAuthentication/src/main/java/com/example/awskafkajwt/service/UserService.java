package com.example.awskafkajwt.service;




import com.example.awskafkajwt.entity.User;
import com.example.awskafkajwt.security.payload.RegisterRequest;

import java.util.List;

public interface UserService {
    User save(RegisterRequest user);
    List<User> findAll();
    User findOne(String username);
    User findById(Long id);
}
