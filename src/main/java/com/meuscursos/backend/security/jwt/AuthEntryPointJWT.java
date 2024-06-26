package com.meuscursos.backend.security.jwt;

//Sobre -> Classe AuthEntryPointJWT:
/*
     Classe AuthEntryPointJWT:
        É um @Componente - Bean do Spring
        Implementa AuthenticationEntryPoint - Interface do Spring Security usada para lidar com falhas de autenticação.
        Realiza um override de commence - Este método é chamado sempre que uma tentativa de autenticação falha.
            commence -> Recebe a requisição (HttpServletRequest), a resposta (HttpServletResponse) e a exceção de autenticação (AuthenticationException).

            Importância: Define como a aplicação responde a uma falha de autenticação, normalmente configurando a resposta HTTP adequadamente.

    Configuração da Resposta HTTP:
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); -> Tipo de conteúdo da resposta como JSON
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); -> Status HTTP como 401 Unauthorized.

        Importância: Informar ao cliente (e.g., navegador, API client) que a requisição falhou devido à falta de autenticação e que o formato da resposta é JSON.

    Corpo da Resposta:
        final Map<String, Object> body = new HashMap<>(); ->  Cria um mapa contendo detalhes sobre o erro, como o status e uma mensagem de erro.
            body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            body.put("error", "Unauthorized");

    Escrita do Corpo na Resposta:
        final ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), body); -> Usa a biblioteca Jackson (ObjectMapper) para escrever o mapa como JSON na saída da resposta HTTP.
            Serializa o mapa para JSON e o envia na resposta, garantindo que o cliente receba os detalhes do erro em um formato legível e estruturado.


    Importância da Classe

        Feedback ao Cliente: Fornece uma resposta clara e consistente ao cliente quando a autenticação falha, incluindo o status e uma mensagem de erro.

        Segurança: Garante que tentativas de acesso não autenticado sejam interceptadas e respondidas adequadamente.

        Integração com Spring Security: Facilita a integração com a configuração de segurança do Spring, permitindo uma gestão centralizada e customizada da segurança.

        Sem esta Classe a aplicação a resposta padrão do Spring Security a uma falha de autenticação pode ser menos informativa ou consistente com as necessidades da aplicação. O cliente pode receber respostas genéricas ou HTML, o que não é ideal para APIs REST.

 */


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJWT implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unuathorized");

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);


    }



}
