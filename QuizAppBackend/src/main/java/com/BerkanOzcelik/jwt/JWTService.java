package com.BerkanOzcelik.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Base64.Decoder;
import java.util.function.Function;

import com.BerkanOzcelik.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    public static final String SECRET_KEY = "6cYJXu8stPkDVjECXe8pgLI0612LApCzNEd/ciEWJjo=";

    // 2) setSubject(...) → Token kimin için oluşturuluyor? (username)

    // setIssuedAt(...) → Ne zaman oluşturuldu?

    // setExpiration(...) → Ne zaman geçersiz olacak?

    // signWith(...) → İmza ekleniyor (getKey() kullanılıyor)

    // Login işleminden sonra bu çağrılır.

    // Kullanıcıya geri dönecek token burada üretilir.

    public String generateToken(UserDetails userDetails) {
        User user = (User) userDetails;

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", user.getUserRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    // 4 Generic fonksiyon → istediğin türde bilgi dönebilir.

    // Claims::getSubject → username döndürür

    // Claims::getExpiration → expire tarihi döndürür

    // Tekrarlı kodları engeller. DRY prensibine uygun.

    public <T> T exportToken(String token, Function<Claims, T> claimsFunc) {
        Claims claims = getClaims(token);
        return claimsFunc.apply(claims);
    }
    // 3 parserBuilder() → Token çözümleme için yapı kurar.

    // parseClaimsJws(token) → Token’ı doğrular ve içindeki bilgileri alır.

    // getBody() → Token’ın içinde yer alan Claims alanı (subject, expiration vs.)

    // Token’ı okuyabilmek için önce bu method yazılır. Diğer methodlar bunu
    // çağırır.

    public Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims;

    }
    // 5 ) Token’daki Kullanıcıyı Çıkarır
    // Token’ın kime ait olduğunu bulur.

    // Genelde JWT filtrelerinde çağrılır.

    // Authentication nesnesi üretmeden önce bu kullanılır.
    public String getUsernameByToken(String token) {
        return exportToken(token, Claims::getSubject);
    }
    //6  Token Süresi Geçmiş mi?
    public boolean isTokenValid(String token) {
        Date expireDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expireDate);
    }

    // 1 )JWT oluşturmak ve çözmek için bir anahtara (secret key) ihtiyaç var. Bu
    // method, hem token oluştururken hem de çözümleme yaparken kullanılacak.
    public Key getKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public String getRoleByToken(String token) {
        return exportToken(token, claims -> claims.get("role", String.class));
    }

}
