package com.giancarlohaack.cursoudemy01.services;

import com.giancarlohaack.cursoudemy01.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);
    void sendEmail(SimpleMailMessage msg);

}
