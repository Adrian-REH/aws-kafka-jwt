package com.example.awskafkajwt.service;

import com.example.awskafkajwt.entity.User;
import com.example.awskafkajwt.repository.UserRepository;
import com.example.awskafkajwt.security.config.TokenProvider;
import com.example.awskafkajwt.security.payload.JwtResponse;
import com.example.awskafkajwt.security.payload.LoginRequest;
import com.example.awskafkajwt.security.payload.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que lleva a cabo la autenticación utilizando JWT
 *
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 *
 *
 */
@Service
public class AuthService {


    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder encoder;
    private final TokenProvider jwtTokenUtil;

    public AuthService(AuthenticationManager authManager,
                       UserRepository userRepository,
                       UserService userService, PasswordEncoder encoder,
                       TokenProvider jwtTokenUtil){
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public JwtResponse login(LoginRequest loginRequest){

        final Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        return new JwtResponse(token);
    }



    public User register( RegisterRequest signUpRequest) {
        return userService.save(signUpRequest);
    }


    public User recuperar(){
        return new User();
    }

    public User borrar(){
        return new User();
    }


}
