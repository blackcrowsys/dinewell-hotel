package com.blackcrowsys.dinewell.hotel.security.controller;

import com.blackcrowsys.dinewell.hotel.security.dto.LoginForm;
import com.blackcrowsys.dinewell.hotel.security.service.AutenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer";

    @Autowired
    private AutenticationService service;

    @PostMapping
    public ResponseEntity<String> authenticateUser(@Valid LoginForm form, HttpServletResponse response) {
        ResponseEntity<String> responseEntity = service.authenticate(form);
        if (responseEntity.hasBody()) {
            response.setHeader(AUTH_HEADER, String.format("%s %s", BEARER, responseEntity.getBody()));
            return responseEntity;
        }
        return responseEntity;
    }
}
