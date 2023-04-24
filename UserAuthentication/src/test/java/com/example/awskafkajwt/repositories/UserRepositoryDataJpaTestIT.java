package com.example.awskafkajwt.repositories;

import com.example.awskafkajwt.entity.Role;
import com.example.awskafkajwt.entity.User;
import com.example.awskafkajwt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class UserRepositoryDataJpaTestIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void whenSaveUsername_thenReturnUser(){
        User user = createUser();
        entityManager.persist(user);
        entityManager.flush();

        User b = userRepository.save(user);
        assertThat(b.getUsername())
                .isEqualTo(user.getUsername());


        b.getRoles().forEach( role -> {
            assertThat(role.getName())
                    .isEqualTo("ADMIN");
            }
        );

    }
    @Test
    public void whenFindByUsername_thenReturnUser(){
        User user = createUser();
        entityManager.persist(user);
        entityManager.flush();

        User b = userRepository.findByUsername("Adrian");
        assertThat(b.getUsername())
                .isEqualTo(user.getUsername());

    }
    @Test
    public void whenExistsByEmail_thenReturnBoolean(){
        User user = createUser();
        entityManager.persist(user);
        entityManager.flush();


        assertThat(userRepository.existsByEmail("adrian@gmail.com"))
                .isEqualTo(user.getEmail().contentEquals("adrian@gmail.com"));

    }
    @Test
    public void whenExistsByUsername_thenReturnBoolean(){
        User user = createUser();
        entityManager.persist(user);
        entityManager.flush();


        assertThat(userRepository.existsByEmail("Adrian"))
                .isEqualTo(user.getEmail().contentEquals("Adrian"));

    }

    private User createUser() {
        User user = new User();
        Role role = new Role();

        role.setName("ADMIN");
        role.setDescription("admin role");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);


        user.setUsername("Adrian");
        user.setEmail("adrian@gmail.com");
        user.setPassword("dexter");
        user.setRoles( roleSet);
        return user;
    }
}
