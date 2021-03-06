 package br.com.o.q.leidson.componentes.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.o.q.leidson.componentes.domain.FabricanteModelo;
import br.com.o.q.leidson.componentes.domain.Usuario;

public abstract class AbstractEmailService implements EmailService {
	
	@Value ("${default.sender}")
	private String sender;
	
	
	@Override
	public void sendOrderConfirmationEmail (FabricanteModelo obj) {

	}
	
	public void sendDecomposicao (FabricanteModelo obj, String email) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromFabricanteModelo (obj, email);
		sendEmail(sm);
	}
	
	public void sendEstrategicas (StringBuilder assunto, StringBuilder estrategicas, String email) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromEstrategicas (assunto, estrategicas, email);
		sendEmail(sm);
	}


	protected SimpleMailMessage prepareSimpleMailMessageFromFabricanteModelo(FabricanteModelo obj, String email) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(email);
		sm.setFrom(sender);
		sm.setSubject(obj.getFabricante()+"-"+obj.getModelo());
		sm.setText(obj.toString());
		return sm;
	}
	
	
	protected SimpleMailMessage prepareSimpleMailMessageFromEstrategicas(StringBuilder assunto, StringBuilder estrategicas, String email) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(email);
		sm.setFrom(sender);
		sm.setSubject(assunto.toString());
		sm.setText(estrategicas.toString());
		return sm;
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm);
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
	
}
