package com.example.awskafkajwt.service.impl;

import com.example.awskafkajwt.entity.Role;
import com.example.awskafkajwt.repository.RoleRepository;
import com.example.awskafkajwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
