package com.BerkanOzcelik.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BerkanOzcelik.controller.IRestAuthenticationController;
import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.dto.AuthRequest;
import com.BerkanOzcelik.dto.AuthResponse;
import com.BerkanOzcelik.dto.DtoUser;
import com.BerkanOzcelik.dto.RefreshTokenRequest;
import com.BerkanOzcelik.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerIml extends RestBaseController implements IRestAuthenticationController  {
   
    @Autowired
    private IAuthenticationService authenticationService;
   
   
    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
       return ok(authenticationService.register(input));
    }

    @PostMapping("/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {

        return ok(authenticationService.authenticate(input));

    }
    @PostMapping("/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
        return ok(authenticationService.refreshToken(input));
    }   

}