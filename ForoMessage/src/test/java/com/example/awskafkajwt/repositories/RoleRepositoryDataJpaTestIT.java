package com.example.awskafkajwt.repositories;

import com.example.awskafkajwt.entity.Role;
import com.example.awskafkajwt.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
class RoleRepositoryDataJpaTestIT {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    void findRoleByName() {


        Role b = roleRepository.findRoleByName("ADMIN");
        assertThat(b.getName())
                .isEqualTo("ADMIN");

         b = roleRepository.findRoleByName("USER");
        assertThat(b.getName())
                .isEqualTo("USER");

         b = roleRepository.findRoleByName("MANAGER");
        assertThat(b.getName())
                .isEqualTo("MANAGER");

    }

}