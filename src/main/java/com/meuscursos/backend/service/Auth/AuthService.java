package com.meuscursos.backend.service.Auth;

import com.meuscursos.backend.dto.Auth.AccessDTO;
import com.meuscursos.backend.dto.Auth.AuthenticationDTO;
import com.meuscursos.backend.dto.Auth.UserDetailsImplDTO;
import com.meuscursos.backend.security.jwt.JwtMethodsUtils;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


//
//@Service
//public class AuthService {
//
//    @Autowired
//    private AuthenticationManager authenticatioManager;
//
//    @Autowired
//    private JwtMethodsUtils jwtUtils;
//
//    public AccessDTO login(AuthenticationDTO authDto) {
//
//        try {
//            //Cria mecanismo de credencial para o spring
//            UsernamePasswordAuthenticationToken userAuth =
//                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
//
//            //Prepara mecanismo para autenticacao
//            Authentication authentication = authenticatioManager.authenticate(userAuth);
//
//            //Busca usuario logado
//            UserDetailsImplDTO userAuthenticate = (UserDetailsImplDTO)authentication.getPrincipal();
//
//            String token = jwtUtils.generateTokenFromUserDetailsImplDTO(userAuthenticate);
//
//            AccessDTO accessDto = new AccessDTO(token);
//
//            return accessDto;
//
//        }catch(BadCredentialsException e) {
//            //TODO LOGIN OU SENHA INVALIDO
//        }
//        return new AccessDTO("Acesso negado");
//    }
//}
//



@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticatioManager;

    @Autowired
    private JwtMethodsUtils jwtMethodsUtils;

    public AccessDTO login(AuthenticationDTO authenticationDTO){

        try{

            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

            Authentication authentication = authenticatioManager.authenticate(userAuth);

            UserDetailsImplDTO userAuthenticated = (UserDetailsImplDTO) authentication.getPrincipal();

            String tokenFromUserAuthenticated = jwtMethodsUtils.generateTokenFromUserDetailsImplDTO(userAuthenticated);

            AccessDTO accessDTO = new AccessDTO(tokenFromUserAuthenticated);
            System.out.println(accessDTO.getToken()+" token em acessoDTO - ");
            return accessDTO;

        } catch (BadCredentialsException e){
            System.out.println("Erro no Auth Service"+e.getMessage());
//            throw new BadCredentialsException("Erro ao processar token JWT: " + e.getMessage());
        }
         return new AccessDTO("Acesso negado");

    }
}
