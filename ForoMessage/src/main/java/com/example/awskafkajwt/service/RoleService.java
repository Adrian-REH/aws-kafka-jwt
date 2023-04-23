package com.example.awskafkajwt.service;


import com.example.awskafkajwt.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
