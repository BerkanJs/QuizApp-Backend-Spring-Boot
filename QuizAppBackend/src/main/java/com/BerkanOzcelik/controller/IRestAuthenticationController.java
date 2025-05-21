package com.BerkanOzcelik.controller;

import com.BerkanOzcelik.dto.*;

import java.util.List;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(DtoUserIU input);


    public RootEntity <AuthResponse> authenticate (AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

    public RootEntity<List<DtoUser>> getAllUsers();

    
} 