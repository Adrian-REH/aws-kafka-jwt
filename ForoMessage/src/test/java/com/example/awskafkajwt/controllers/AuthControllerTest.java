package com.example.awskafkajwt.controllers;
import com.example.awskafkajwt.entity.User;
import com.example.awskafkajwt.security.payload.JwtResponse;
import com.example.awskafkajwt.security.payload.LoginRequest;
import com.example.awskafkajwt.security.payload.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BAD TEST USE ONLY MOCKSPRINGTEST
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
class AuthControllerTest {
    private final RegisterRequest registerRequest = new RegisterRequest();
    private final LoginRequest loginRequest = new LoginRequest();

    private final HttpHeaders headers = new HttpHeaders();
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        //restTemplateBuilder = restTemplateBuilder.defaultHeader("Authorization","sd").rootUri("http://localhost:"+port);
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate= new TestRestTemplate(restTemplateBuilder);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


    }


    @Test
    void whenLogin_thenReturnJwtResponse() {
        loginRequest.setUsername("adrian");
        loginRequest.setPassword("1234");
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);
        ResponseEntity<JwtResponse> response = testRestTemplate.postForEntity("/api/auth/login",request, JwtResponse.class);

        JwtResponse result = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertNotEquals(null, result.getToken());

    }

    @Test
    void whenLoginRequestNull_thenReturnJwtResponseNull() {
        ResponseEntity<JwtResponse> response = testRestTemplate.postForEntity("/api/auth/login",null, JwtResponse.class);

        JwtResponse result = response.getBody();
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
        assertEquals(415, response.getStatusCodeValue());
        assertEquals(null, result.getToken());

        System.out.println(result);

    }

    @Test
    void whenRegisterUser_thenReturnMessageResponse() {
        registerRequest.setUsername("adm");//Se guarda en el servidor por ende hay que tener cuidado
        registerRequest.setEmail("adr@gmail.com");
        registerRequest.setPassword("1234");
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/auth/register",request, User.class);

        System.out.println(response.getBody());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        //assertEquals("adm", response.getBody().getRoles());

        System.out.println(response.getBody().getRoles());
    }
    
    @Test
    void whenRegisterAdmin_thenReturnMessageResponse() {
        registerRequest.setUsername("adm");//Se guarda en el servidor por ende hay que tener cuidado
        registerRequest.setEmail("adr@admin.es");
        registerRequest.setPassword("1234");
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/auth/register",request, User.class);

        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        response.getBody().getRoles().forEach(role -> {
            System.out.println(role.getName());
            assertEquals("USER", role.getName());
            assertEquals("User role", role.getDescription());

        });

    }

    @Test
    void whenRegisterRequestNull_thenReturnMessageResponseUMT() {
        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/auth/register",null, User.class);

        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, response.getStatusCode());
        assertEquals(415, response.getStatusCodeValue());

    }

    @Test
    void whenRegisterRequestParmNull_thenReturnMessageResponseISE() {
        registerRequest.setUsername(null);
        registerRequest.setEmail(null);
        registerRequest.setPassword(null);
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/auth/register",request, User.class);


        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        assertNull(response.getBody().getUsername());


    }
    
    @Test
    void whenRegisterRequestParmIsBlank_thenReturnMessageResponseISE() {
        registerRequest.setUsername("");
        registerRequest.setEmail("");
        registerRequest.setPassword("");
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/auth/register",request, User.class);


        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        assertNull( response.getBody().getUsername());


    }

    @Test
    void whenRegisterUsernameExist_thenReturnMessageResponseUsernameExist() {
        registerRequest.setUsername("adrian");
        registerRequest.setEmail("@");
        registerRequest.setPassword("1234");
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/auth/register",request, User.class);


        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        assertNull( response.getBody().getUsername());

    }
    
    @Test
    void whenRegisterEmailExist_thenReturnMessageResponseEmailExist() {
        registerRequest.setUsername("dex");
        registerRequest.setEmail("adrian@gmail.com");
        registerRequest.setPassword("1234");
        HttpEntity<RegisterRequest> request = new HttpEntity<>(registerRequest, headers);

        ResponseEntity<User> response = testRestTemplate.postForEntity("/api/auth/register",request, User.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCodeValue());
        assertNull( response.getBody().getUsername());

    }

    @Test
    void AdminHelloHowAdmin(){
        generoToken("adrian2");

        HttpEntity<LoginRequest> request = new HttpEntity<>( headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/auth/hello-admin", HttpMethod.GET,request, String.class);

        String result = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Hola!, bienvenido a la seccion Administracion", result);


    }

    @Test
    void AdminHelloHowUser(){
         generoToken("adrian");

        HttpEntity<LoginRequest> request = new HttpEntity<>( headers);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/auth/hello-admin", HttpMethod.GET,request, String.class);

        String result = response.getBody();
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(403, response.getStatusCodeValue());
        assertNotEquals("Hola!, bienvenido a la seccion Administracion", result);

    }

    void generoToken(String username) {
        loginRequest.setUsername(username);
        loginRequest.setPassword("1234");
        HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);
        ResponseEntity<JwtResponse> response = testRestTemplate.postForEntity("/api/auth/login", request, JwtResponse.class);
        JwtResponse result = response.getBody();

        headers.setBearerAuth(result.getToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    }


}