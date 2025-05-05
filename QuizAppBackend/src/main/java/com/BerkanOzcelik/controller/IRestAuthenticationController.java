package com.BerkanOzcelik.controller;

import com.BerkanOzcelik.dto.AuthRequest;
import com.BerkanOzcelik.dto.AuthResponse;
import com.BerkanOzcelik.dto.DtoUser;
import com.BerkanOzcelik.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

     public RootEntity<DtoUser> register (AuthRequest input);

    public RootEntity <AuthResponse> authenticate (AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

    
} 