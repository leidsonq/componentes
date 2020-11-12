package br.com.o.q.leidson.componentes.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.o.q.leidson.componentes.domain.FabricanteModelo;

public abstract class AbstractEmailService implements EmailService {
	
	@Value ("${default.sender}")
	private String sender;
	
	
	@Override
	public void sendOrderConfirmationEmail (FabricanteModelo obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromFabricanteModelo (obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromFabricanteModelo(FabricanteModelo obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo("leidsonoliveira@yahoo.com.br");
		sm.setFrom(sender);
		sm.setSubject(obj.getFabricante()+"-"+obj.getModelo());
		sm.setText(obj.toString());
		return sm;
	}

}
