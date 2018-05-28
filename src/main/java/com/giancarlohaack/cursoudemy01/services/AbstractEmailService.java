package com.giancarlohaack.cursoudemy01.services;

import com.giancarlohaack.cursoudemy01.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public abstract class AbstractEmailService implements EmailService{

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {

        return null;
    }
}
