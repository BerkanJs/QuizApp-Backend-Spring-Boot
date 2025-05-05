package com.BerkanOzcelik.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.BerkanOzcelik.exception.BaseException;
import com.BerkanOzcelik.exception.ErrorMessage;
import com.BerkanOzcelik.exception.MessageType;
import com.BerkanOzcelik.model.User;
import com.BerkanOzcelik.repository.UserRepository;





@Configuration
public class AppConfig {
    
    @Autowired
    private UserRepository userRepository;




    @Bean
    public UserDetailsService userDetailsService(){   //Kullanıcı Doğrulama Servisi ,Spring Security’nin kullanıcıyı tanıyabilmesi için gerekli.
        return new UserDetailsService() {             // JWT ile giriş yapıldığında username çekilir ve bu service üzerinden kullanıcıyı sistemde arar.
                                                      //UserDetails tipinde dönmelidir.  User sınıfı da bu interface’i implement etmiş olmalı.   
            
            @Override
            public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
                Optional<User> optional = userRepository.findByUsername(username);
                if(optional.isEmpty()){

                    throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));

                }
             
                return optional.get();
            }
        };
    
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){  //Kimlik Doğrulama Sağlayıcısı
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();  //"Kullanıcı adı şifresiyle giriş yapmak istiyor. Bu bilgileri hangi UserDetailsService ile doğrulayacağım
        provider.setUserDetailsService(userDetailsService());                  //DaoAuthenticationProvider bunu sağlar. Şifre karşılaştırmasını yapabilmesi için ayrıca bir PasswordEncoder gerekir. (BCrypt)
        provider.setPasswordEncoder(passwordEncoder());                       
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{ //Doğrulama Yöneticisi
        return configuration.getAuthenticationManager();             //Login işlemlerinde AuthenticationManager kullanılır.
                                                                     //AuthService gibi servislerinde authenticationManager.authenticate(...) yapabilmek için bu gerekiyor.
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ //Şifreleme Mekanizması
        return new BCryptPasswordEncoder();
    }


}



//Eğer AppConfig sınıfı olmazsa:

//Spring AuthenticationManager’ı bulamaz.

//UserDetailsService bean olarak tanımlı değilse JwtFilter çalışmaz.

//PasswordEncoder tanımlı değilse IllegalArgumentException fırlatılır.



//JWT sisteminde:

//AuthController → Kullanıcı giriş yapmak ister.

//AuthenticationManager.authenticate(...) çağrılır → AppConfig sayesinde çalışır.

//Kullanıcı adıyla UserDetailsService çağrılır → veritabanından kullanıcı çekilir.

//Şifre PasswordEncoder ile doğrulanır.

//Başarılıysa JWTService.generateToken() çağrılır.

//Sonraki isteklerde JWTAuthenticationFilter token'ı doğrular → yine UserDetailsService kullanır.

//JWTService'in doğru çalışabilmesi için AppConfig tarafından sağlanan bazı bileşenlere (özellikle UserDetailsService ve AuthenticationManager) ihtiyaç vardır.

//JWTAuthenticationFilter, Spring Security'nin bir filter'ıdır ve HTTP isteklerini kontrol eder. AppConfig ise bu JWTAuthenticationFilter'ı düzgün şekilde Spring Security yapılandırmalarına dahil etmek için kullanılır.


//AppConfig = Altyapıyı Hazırlayan Orkestra Şefi
//JWTService ve JWTAuthenticationFilter'ın birbiriyle uyumlu çalışabilmesi için ihtiyaç duyduğu temel bileşenleri (UserDetailsService, AuthenticationManager, PasswordEncoder, vs.) Spring’e "bak bu bean'leri kullan" diyerek hazırlar. Bu da uygulamanın düzgün ve güvenli şekilde işlemesini sağlar.



