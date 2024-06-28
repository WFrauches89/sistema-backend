package com.meuscursos.backend.service;

import com.meuscursos.backend.dto.UsuarioDTO;
import com.meuscursos.backend.entity.email.VerificadorEmailEntity;
import com.meuscursos.backend.entity.enums.UserStatusEnumType;
import com.meuscursos.backend.entity.UsuarioEntity;
import com.meuscursos.backend.repository.UsuarioRepository;
import com.meuscursos.backend.repository.email.VerificadorEmailRepository;
import com.meuscursos.backend.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificadorEmailRepository vEmailRepository;

    public List<UsuarioDTO> getAllUsers(){
        List<UsuarioEntity> listaUsuariosEntity = usuarioRepository.findAll();
        return listaUsuariosEntity.stream().map(UsuarioDTO::new).toList();
    }
    public UsuarioDTO getUserByID(Long id){
        return new UsuarioDTO(usuarioRepository.getReferenceById(id));

    }

    public void createUser(UsuarioDTO usuarioDTO, String word){
        UsuarioEntity usuario = new UsuarioEntity(usuarioDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        if (usuario.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new users");
        }
        if(word.equals("newUserPublic")){
            usuario.setStatus(UserStatusEnumType.PENDENTE);
            usuarioRepository.save(usuario);

            VerificadorEmailEntity vEE = new VerificadorEmailEntity();
            vEE.setUsuario(usuario);
            vEE.setExpiration(Instant.now().plusMillis(900000));
            vEE.setUuid(UUID.randomUUID());

            vEmailRepository.save(vEE);

            emailService.emailTextSend(usuarioDTO.getEmail(),
                    "Seu cadastro foi realizado...",
                    "Para dar continuidade, favor clicar no link de ativação de usuário. Código verificador: "+vEE.getUuid());


        } else {
            usuarioRepository.save(usuario);
        }
    }

    public UsuarioDTO updateUsers(UsuarioDTO usuarioDto){
        UsuarioEntity usuario = new UsuarioEntity(usuarioDto);
        usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        return new UsuarioDTO(usuarioRepository.save(usuario));

    }

    public void deleteUser(Long id){
        usuarioRepository.delete(usuarioRepository.getReferenceById(id));
    }

    public String verificaUserEmail(String uuid) {

        Optional<VerificadorEmailEntity> verificado = (vEmailRepository.findByUuid(UUID.fromString(uuid)));

        if(verificado.isPresent()){
            VerificadorEmailEntity uuidVerificado = verificado.get();
            if(uuidVerificado.getExpiration().compareTo(Instant.now() )>=0){

                UsuarioEntity uGet = uuidVerificado.getUsuario();
                uGet.setStatus(UserStatusEnumType.ATIVO);

                usuarioRepository.save(uGet);
                return "Retorno Service: Verificado existente e Atualizado";
            } else {
                vEmailRepository.delete(uuidVerificado);
                return "Tempo de verificação expirado";
            }
        } else {
            return "Retorno Service: Verificado inexistente";
        }
    }

}
