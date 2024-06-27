package com.meuscursos.backend.security;
/*
    Para utilizar o sistema de authenticação é necessário criar uma série de classes e métodos que serão responsáveis por realizar as verificações.

    Iniciando temos que inserir no pom as dependencias de security, jwt e GSON...

    Como classe principal temos a classe WebSecurityConfig que lidará com os principais métodos, fazendo a injeção de dependencia e criando a cadeia filtrada de segurança.

    Classe AuthEntryPointJWT
        AuthEntryPointJWT é uma peça vital na arquitetura de segurança de uma aplicação baseada em JWT, garantindo que falhas de autenticação sejam tratadas de maneira consistente e segura.

    PasswordEncoder
        É responsável por codificar e validar senhas de usuários de forma segura.

    AuthentiManager
        Este método configura o AuthenticationManager, que é responsável por autenticar os usuários durante o processo de login na aplicação.

    AuthFilterToken
        É responsável por filtrar cada solicitação HTTP, verificando a presença e a validade de um token JWT.

    SecurityFilterChain
        Este método configura a cadeia de filtros de segurança (SecurityFilterChain) usando o HttpSecurity, que é a configuração central do Spring Security para configurar regras de segurança HTTP.

        Importância:
            Definição de Políticas de Segurança: Configura políticas de segurança como CORS, CSRF, gerenciamento de sessão e autorizações de URLs.
            Ordenação de Filtros: Adiciona filtros na ordem correta para processar requisições HTTP, garantindo que a segurança seja aplicada antes
                que as requisições alcancem os endpoints da aplicação.

                Segurança Forte: Configuram senhas seguras, autenticação robusta e autorizações baseadas em papéis.
                Flexibilidade: Permitem a personalização do comportamento de autenticação e autorização conforme necessário.
                Padrões Atuais: Utilizam práticas modernas de segurança, como autenticação baseada em token (JWT) e configurações de segurança HTTP avançadas.
 */

import com.meuscursos.backend.security.jwt.AuthEntryPointJWT;
import com.meuscursos.backend.security.jwt.AuthFilterToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private AuthEntryPointJWT unauthorizeHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthFilterToken authFilterToken(){
        return new AuthFilterToken();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(Customizer.withDefaults());
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizeHandler))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                                            .requestMatchers("/usuarios/**").permitAll()
                                                            .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                                                            .anyRequest().authenticated());

        httpSecurity.addFilterBefore(authFilterToken(),UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

    }


}
