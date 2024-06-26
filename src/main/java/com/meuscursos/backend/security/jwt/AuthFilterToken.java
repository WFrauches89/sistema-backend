package com.meuscursos.backend.security.jwt;

/*
    AuthFilterToken -> estende OncePerRequestFilter do Spring Security e é responsável por filtrar cada solicitação HTTP,
                        verificando a presença e a validade de um token JWT (JSON Web Token) para autenticar o usuário.

    OncePerRequestFilter -> é uma implementação de filtro do Spring Security que garante que a filtragem ocorra apenas uma vez por solicitação.

    doFilterInternal() -> Contém a lógica principal para processar o token JWT e autenticar o usuário.

    getToken() -> Retornar o cabeçalho sem o sufixo "Bearer ".

    userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        Desempenha um papel importante ao associar informações detalhadas da solicitação HTTP ao objeto de autenticação do usuário. Isso não só facilita a auditoria e o rastreamento de atividades de autenticação, mas também suporta a implementação de políticas de segurança mais detalhadas e personalizadas baseadas nas características da solicitação. Essa prática é essencial para um sistema de segurança robusto e eficaz, especialmente em aplicações que exigem controle rigoroso sobre o acesso e auditoria de atividades.

    Importância da Classe AuthFilterToken
        Autenticação Centralizada:
            Garante que todas as solicitações HTTP sejam filtradas e autenticadas de forma consistente, usando o token JWT.
        Segurança:
            Verifica e valida tokens JWT para autenticar usuários, protegendo os endpoints da aplicação.

        Integração com Spring Security:
            Atualiza o contexto de segurança do Spring com os detalhes do usuário autenticado, permitindo que o Spring Security gerencie a autorização com base nas roles e permissões do usuário.

        Facilita a Manutenção:
            Centraliza a lógica de autenticação em um único lugar, facilitando a manutenção e as atualizações no mecanismo de autenticação.

        Flexibilidade e Extensibilidade:
            Pode ser facilmente estendido ou modificado para adicionar lógica adicional de autenticação, como verificação de IP, limitação de taxa, etc.

     No bloco finally, verificamos se a autenticação foi realizada com sucesso (authentication.isAuthenticated()).
        Se não, limpamos o contexto de segurança usando SecurityContextHolder.clearContext().
        Isso garante que quaisquer configurações ou detalhes de autenticação não sejam mantidos na thread atual quando não forem mais necessários.
 */
import com.meuscursos.backend.service.Auth.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilterToken extends OncePerRequestFilter {

    @Autowired
    private JwtMethodsUtils jwtMethodsUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        try{
            String jwtWithoutBearer = getToken(request);
            if(jwtWithoutBearer != null && jwtMethodsUtils.validateJwtToken(jwtWithoutBearer)){

                String usernameTaken = jwtMethodsUtils.getUsernameToken(jwtWithoutBearer);

                UserDetails userDetailsTaken = userDetailsServiceImpl.loadUserByUsername(usernameTaken);

                UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(userDetailsTaken, null, userDetailsTaken.getAuthorities());

                userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userAuth);
            }
        } catch (Exception e){
            System.out.println("Ocorreu um erro ao processar o token");

        } finally {

        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String headerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")) {
            return headerToken.replace("Bearer ","");
        }
        return null;
    }
}
