package com.meuscursos.backend.security.jwt;
/*
     Solicita a criação do UserDetailsImplDTO que implements UserDetails

 */
import com.meuscursos.backend.dto.Auth.UserDetailsImplDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtMethodsUtils {

    @Value("${project.jwtSecret}")
    private String jwtSecret;

    @Value("${project.jwtExpirationTime}")
    private int jwtExpirationTime;
    
    public String generateTokenFromUserDetailsImplDTO(UserDetailsImplDTO userDetailsImplDTO){
        System.out.println("Entrou em generateToken");
        return Jwts.builder().setSubject(userDetailsImplDTO.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationTime))
                .signWith(getSigninKey(), SignatureAlgorithm.HS512).compact();

    }

    private Key getSigninKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsernameToken(String jwtWithoutBearer) {
        System.out.println("Entrou em getUsernameFromToken");
        return Jwts.parser().setSigningKey(getSigninKey()).build()
                .parseClaimsJws(jwtWithoutBearer).getBody().getSubject();
    }

    public boolean validateJwtToken(String jwtWithoutBearer) {
        System.out.println("Entrou em validateToken");
        try {
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(jwtWithoutBearer);
            return true;
        }catch(MalformedJwtException e) {
            System.out.println("Token inválido " + e.getMessage());
        }catch(ExpiredJwtException e) {
            System.out.println("Token expirado " + e.getMessage());
        }catch(UnsupportedJwtException e) {
            System.out.println("Token não suportado " + e.getMessage());
        }catch(IllegalArgumentException e) {
            System.out.println("Token Argumento inválido " + e.getMessage());
        }
        return false;
    }


}
