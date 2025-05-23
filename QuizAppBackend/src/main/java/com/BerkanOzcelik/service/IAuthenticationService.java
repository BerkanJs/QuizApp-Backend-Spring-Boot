package com.BerkanOzcelik.service;

import com.BerkanOzcelik.dto.*;

import java.util.List;

public interface IAuthenticationService {

    public DtoUser register(DtoUserIU input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
    
    public void deleteUser(Long userId);

    public List<DtoUser> getAllUsers() ;


    

}