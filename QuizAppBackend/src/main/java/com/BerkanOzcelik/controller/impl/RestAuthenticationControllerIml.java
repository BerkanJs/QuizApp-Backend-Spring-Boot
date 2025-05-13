package com.BerkanOzcelik.controller.impl;

import com.BerkanOzcelik.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BerkanOzcelik.controller.IRestAuthenticationController;
import com.BerkanOzcelik.controller.RestBaseController;
import com.BerkanOzcelik.controller.RootEntity;
import com.BerkanOzcelik.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerIml extends RestBaseController implements IRestAuthenticationController  {
   
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody DtoUserIU input) {
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