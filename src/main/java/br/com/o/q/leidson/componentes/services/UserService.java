package br.com.o.q.leidson.componentes.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.o.q.leidson.componentes.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}

}
