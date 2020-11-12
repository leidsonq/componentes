package br.com.o.q.leidson.componentes.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.o.q.leidson.componentes.domain.FabricanteModelo;

public interface EmailService {
	
	void sendOrderConfirmationEmail (FabricanteModelo obj);
	
	void sendEmail (SimpleMailMessage msg);

}
