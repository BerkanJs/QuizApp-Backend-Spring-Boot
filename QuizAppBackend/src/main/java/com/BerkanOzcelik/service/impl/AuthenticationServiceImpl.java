package com.BerkanOzcelik.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.BerkanOzcelik.dto.*;
import com.BerkanOzcelik.model.Departments;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.jwt.JWTService;
import com.BerkanOzcelik.model.RefreshToken;
import com.BerkanOzcelik.model.User;
import com.BerkanOzcelik.repository.RefreshTokenRepository;
import com.BerkanOzcelik.repository.UserRepository;
import com.BerkanOzcelik.service.IAuthenticationService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JWTService jwtService;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public DtoUser register(DtoUserIU input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEmail(input.getEmail());
        user.setUserRole(input.getUserRole());

        if (input.getDepartmentId() != null) {
            Departments department = new Departments();
            department.setId(input.getDepartmentId());
            user.setDepartment(department);
        }

        User savedUser = userRepository.save(user);

        DtoUser dtoUser = new DtoUser();
        BeanUtils.copyProperties(savedUser, dtoUser);

        if (savedUser.getDepartment() != null) {
            dtoUser.setDepartmentId(savedUser.getDepartment().getId());
        }

        return dtoUser;
    }



    @Override
    public AuthResponse authenticate(AuthRequest input) {
        try {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    input.getUsername(), input.getPassword());
            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optUser = userRepository.findByUsername(input.getUsername());
            String accessToken = jwtService.generateToken(optUser.get());
            RefreshToken refreshToken = createRefreshToken(optUser.get());
            RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

        }

        catch (Exception e) {

            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));

        }

    }

    public boolean isValideRefreshToken(Date expiredDate) {
        return new Date().before(expiredDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {

        Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
        if (optRefreshToken.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
        }

        if (!isValideRefreshToken(optRefreshToken.get().getExpiredDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, input.getRefreshToken()));
        }
        User user = optRefreshToken.get().getUser();
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = createRefreshToken(user);
        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

    }

    @Override
    public void deleteUser(Long userId) {
      
        Optional<User> userOpt = userRepository.findById(userId);

       
        if (userOpt.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, "Kullanıcı bulunamadı"));
        }

       
        userRepository.deleteById(userId);
    }

}
