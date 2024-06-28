package com.meuscursos.backend.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String emailTextSend(String destinatario, String assunto, String msgm){
        try {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom(remetente);
            smm.setTo(destinatario);
            smm.setSubject(assunto);
            smm.setText(msgm);
            javaMailSender.send(smm);

        }catch (Exception e){
            return "Erro ao tentar enviar email de novo cadastro";
        }
        return "Email enviado com sucesso";
    }


}
