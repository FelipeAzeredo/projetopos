package com.projetopos.projetopos.services;

import com.projetopos.projetopos.domain.Cliente;
import com.projetopos.projetopos.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);

    void sendOrderConfirmationEmail(Pedido obj);
}
