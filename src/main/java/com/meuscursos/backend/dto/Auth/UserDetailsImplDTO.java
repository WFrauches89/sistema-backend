package com.meuscursos.backend.dto.Auth;
/*
    UserDetailsImplDTO -> Seus principais motivos de criação são:
        Acoplamento:

            Implementar UserDetails diretamente em UsuarioEntity cria um acoplamento entre a lógica de segurança
             e a lógica de persistência. Isso significa que qualquer mudança na lógica de segurança pode impactar diretamente a entidade de persistência,
              e vice-versa. Esse acoplamento reduz a flexibilidade e pode levar a problemas de manutenção no futuro.

        Responsabilidade Única:

            Segundo o Princípio da Responsabilidade Única (SRP), uma classe deve ter apenas um motivo para mudar.
            Quando você implementa UserDetails diretamente na entidade, está adicionando responsabilidade de segurança à entidade de persistência,
            violando esse princípio.

        Testabilidade:

            Separar a lógica de segurança em uma classe distinta (UserDetailsImpl) facilita o teste unitário e a simulação (mocking).
            Quando essas responsabilidades estão misturadas, os testes se tornam mais complexos e menos isolados.

        Reutilização e Extensibilidade:

            Ter uma classe separada para UserDetails permite reutilizar a lógica de segurança com diferentes entidades ou fontes de dados
             sem modificar a entidade original. Isso também facilita a extensão do comportamento de segurança sem afetar a estrutura da entidade de persistência.

        Desempenho:

            Em algumas situações, pode ser desejável otimizar a performance carregando apenas os dados necessários para autenticação e autorização.
            Separar UserDetails da entidade permite tal otimização, carregando apenas os dados essenciais quando necessário.

 */


import com.meuscursos.backend.entity.UsuarioEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImplDTO implements UserDetails {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImplDTO(Long id, String name, String username, String password, String email,  Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public static UserDetailsImplDTO build(UsuarioEntity usuario) {

        return new UserDetailsImplDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getEmail(),
                new ArrayList<>());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
