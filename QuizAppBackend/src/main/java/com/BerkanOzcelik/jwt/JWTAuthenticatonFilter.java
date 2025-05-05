package com.BerkanOzcelik.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;

import io.jsonwebtoken.ExpiredJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticatonFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwTservice;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization"); // Header’ı al
        if (header == null) { // Authorization başlığı istek içinde yoksa, filtre hiçbir şey yapmadan bir
                              // sonraki filtreye geçer.
            filterChain.doFilter(request, response);
            return;
        }

        String token;
        String username;

        token = header.substring(7); // Token ve Kullanıcıyı Al
        try {
            username = jwTservice.getUsernameByToken(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // Eğer kullanıcı
                                                                                                      // daha önce
                                                                                                      // doğrulanmamışsa,
                                                                                                      // işleme devam
                                                                                                      // edilir.
                UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Kullanıcıyı Yükle ve
                                                                                           // Token’ı Doğrula
                if (userDetails != null && jwTservice.isTokenValid(token)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( // Spring'e
                                                                                                                       // Bu
                                                                                                                       // Kullanıcının
                                                                                                                       // Doğrulandığını
                                                                                                                       // Bildir
                            username, userDetails, userDetails.getAuthorities());
                    authenticationToken.setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }

            }
        } catch (ExpiredJwtException ex) { // Hata Yönetimi
            throw new BaseException(new ErrorMessage(MessageType.TOKEN_IS_EXPIRED, ex.getMessage()));
        }

        catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));

        }

        filterChain.doFilter(request, response); // Filtreyi Devam Ettir //Yani → bu filtre işlemini bitirince sıradaki
                                                 // işleme geçilir (diğer filtreler ya da controller).

    }

}

// Method: doFilterInternal(...)

// Gelen her isteği alır.

// Eğer Authorization header’ında JWT varsa, onu işler.

// Token geçerliyse → kullanıcıyı SecurityContextHolder içine koyar → Spring
// artık bu kullanıcıyı “authenticated” kabul eder.


