package com.meuscursos.backend.service;

import com.meuscursos.backend.dto.UsuarioDTO;
import com.meuscursos.backend.entity.UsuarioEntity;
import com.meuscursos.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> getAllUsers(){
        List<UsuarioEntity> listaUsuariosEntity = usuarioRepository.findAll();
        return listaUsuariosEntity.stream().map(UsuarioDTO::new).toList();
    }
    public UsuarioDTO getUserByID(Long id){
        return new UsuarioDTO(usuarioRepository.getReferenceById(id));

    }

    public void createUser(UsuarioDTO usuarioDTO){
        UsuarioEntity usuario = new UsuarioEntity(usuarioDTO);
        System.out.println("usuário antes do encode: "+ usuario.getSenha());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        System.out.println("usuário depois do encode: "+ usuario.getSenha());
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO updateUsers(UsuarioDTO usuarioDto){
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);
        usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        return new UsuarioDTO(usuarioRepository.save(usuario));

    }

    public void deleteUser(Long id){
        usuarioRepository.delete(usuarioRepository.getReferenceById(id));
    }

//    public void createNewUser(UsuarioDTO usuario) {
//        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
//        usuarioEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
//        usuarioEntity.setSituacao(TipoSituacaoUsuario.PENDENTE);
//        usuarioEntity.setId(null);
//        usuarioRepository.save(usuarioEntity);
//
//        UsuarioVerificadorEntity verificador = new UsuarioVerificadorEntity();
//        verificador.setUsuario(usuarioEntity);
//        verificador.setUuid(UUID.randomUUID());
//        verificador.setDataExpiracao(Instant.now().plusMillis(900000));
//        usuarioVerificadorRepository.save(verificador);
//
//        //TODO - Enviar um email para verificar a conta
//        emailService.enviarEmailTexto(usuario.getEmail(),
//                "Novo usuário cadastrado",
//                "Você está recebendo um email de cadastro o número para validação é " + verificador.getUuid());
//
//    }
//
//    public String verificarCadastro(String uuid) {
//
//        UsuarioVerificadorEntity usuarioVerificacao = usuarioVerificadorRepository.findByUuid(UUID.fromString(uuid)).get();
//
//        if(usuarioVerificacao != null) {
//            if(usuarioVerificacao.getDataExpiracao().compareTo(Instant.now()) >= 0) {
//
//                UsuarioEntity u = usuarioVerificacao.getUsuario();
//                u.setSituacao(TipoSituacaoUsuario.ATIVO);
//
//                usuarioRepository.save(u);
//
//                return "Usuário Verificado";
//            }else {
//                usuarioVerificadorRepository.delete(usuarioVerificacao);
//                return "Tempo de verificação expirado";
//            }
//        }else {
//            return "Usuario não verificado";
//        }
//
//    }


}
