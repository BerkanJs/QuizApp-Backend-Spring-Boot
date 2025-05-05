package com.BerkanOzcelik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.BerkanOzcelik.handler.AuthEntryPoint;
import com.BerkanOzcelik.jwt.JWTAuthenticatonFilter;

import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String REGISTER = "/register";
    public static final String AUTHENTICATE = "/authenticate";
    public static final String REFRESH_TOKEN = "/refreshToken";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTAuthenticatonFilter jwtAyAuthenticatonFilter;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(request ->
                request
                    .requestMatchers(REGISTER, AUTHENTICATE, REFRESH_TOKEN).permitAll()
                    .anyRequest().authenticated()
            )
            .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ).authenticationProvider(authenticationProvider).addFilterBefore(jwtAyAuthenticatonFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}




//SecurityConfig Sınıfı Nedir?
//Bu sınıf, Spring Security yapılandırmasını yapar. Yani:

//Hangi endpoint’ler güvenli olacak?

//Hangi filtre ne zaman çalışacak?

//Hangi hatalar nasıl karşılanacak?

//Session yönetimi nasıl olacak?

//Hepsini burada tanımlıyoruz.




//Peki Bu Yapılmasaydı Ne Olurdu?
//Hiçbir endpoint koruma altında olmazdı.

//Token filtremiz çalışmazdı.

//Yetkisiz kullanıcılar endpoint’lere erişebilirdi.

//Hatalar düzgün mesajlarla dönmezdi.

//Spring default login ekranı çıkabilirdi.

//Uygulama güvenli olmazdı.

