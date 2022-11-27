package com.solbs.unov3.controllers;

import com.solbs.unov3.services.TokenService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/token")
@CrossOrigin("*")
public class AuthController {

    private final TokenService tokenService;


    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping()
    public String token(Authentication authentication){
        String token = tokenService.generateToken(authentication);
        return token;
    }
}
