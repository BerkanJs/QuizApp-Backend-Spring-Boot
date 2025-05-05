package com.BerkanOzcelik.repository;

import org.springframework.stereotype.Repository;

import com.BerkanOzcelik.model.RefreshToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RefreshTokenRepository  extends JpaRepository <RefreshToken,Long> {
    Optional<RefreshToken> findByRefreshToken (String refreshToken);

    


    
}
