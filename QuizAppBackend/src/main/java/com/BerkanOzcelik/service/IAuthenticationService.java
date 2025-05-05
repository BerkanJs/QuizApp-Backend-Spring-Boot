package com.BerkanOzcelik.service;

import com.BerkanOzcelik.dto.AuthRequest;
import com.BerkanOzcelik.dto.AuthResponse;
import com.BerkanOzcelik.dto.DtoUser;
import com.BerkanOzcelik.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
    
    public void deleteUser(Long userId);


    

}