package com.meuscursos.backend.service.Auth;

/*
    UserDetailsServiceImpl -> É responsável pela lógica de busca do usuário,
                                fornecendo uma ponte entre a camada de persistência (repositório)
                                e a camada de segurança (Spring Security).

    Implementa UserDetailsService que é uma interface do Spring Security que define um método para carregar os detalhes do usuário por nome de usuário.
    Faz a DI e UsuarioRepository

    Principais responsabilidades...

            1. Separação de Responsabilidades
                UserDetailsService é uma interface do Spring Security que define o método loadUserByUsername para buscar um usuário baseado no nome de usuário. A implementação dessa interface, como UserDetailsServiceImpl, separa claramente a lógica de recuperação de dados de usuários da lógica de segurança. Isso segue o princípio de responsabilidade única (SRP - Single Responsibility Principle), tornando o código mais modular, fácil de entender e de manter.

            2. Facilidade de Integração com Fontes de Dados
                Ao implementar UserDetailsService, você pode definir como os usuários serão recuperados de diferentes fontes de dados. Por exemplo:

                Banco de Dados: Usando um repositório JPA para buscar usuários.
                Serviços Externos: Chamadas de API para um serviço de autenticação externo.
                In-memory Data: Para testes ou protótipos, você pode ter uma implementação que usa dados em memória.

            3. Flexibilidade e Extensibilidade
                Implementar sua própria UserDetailsService permite adicionar lógica adicional necessária para carregar os detalhes do usuário:

                Customização de Autenticação: Pode incluir lógica adicional de autenticação, como bloqueio de conta após várias tentativas falhas, verificação de status do usuário, etc.
                Autorizações Customizadas: Pode carregar informações de autorização específicas que não estão diretamente disponíveis no banco de dados.

            4. Facilita Testes e Mocking
                Separar a lógica de recuperação de usuários em uma implementação de UserDetailsService facilita o teste unitário e de integração:

                Mocking: Em testes, você pode facilmente mockar a UserDetailsService para simular diferentes cenários de autenticação sem precisar de um banco de dados real.
                Testes Unitários: Testar a lógica de recuperação de usuários de forma isolada, sem depender de outros componentes do sistema.

            5. Coesão com Outras Configurações de Segurança
                Integrar UserDetailsService com outras configurações de segurança do Spring Security (como PasswordEncoder, AuthenticationManager, etc.) é mais direto quando você tem uma implementação dedicada:

                Configuração de Senhas: Definir como as senhas são codificadas e verificadas.
                Configuração de Autenticação: Facilita a configuração do AuthenticationManager com a lógica de autenticação personalizada.


 */

import com.meuscursos.backend.dto.Auth.UserDetailsImplDTO;
import com.meuscursos.backend.entity.UsuarioEntity;
import com.meuscursos.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = repository.findByLogin(username).get();
        return UserDetailsImplDTO.build(usuario);
    }
}
