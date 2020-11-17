package br.com.o.q.leidson.componentes.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.o.q.leidson.componentes.domain.Usuario;
import br.com.o.q.leidson.componentes.dto.UsuarioDTO;
import br.com.o.q.leidson.componentes.repositories.UsuarioRepository;
import br.com.o.q.leidson.componentes.resources.exceptions.FieldMessageNome;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioDTO> {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(UsuarioUpdate ann) {
	}

	@Override
	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessageNome> list = new ArrayList<>();
		
		Usuario aux = usuarioRepository.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessageNome("email", "Email já existente"));
		}

		for (FieldMessageNome e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldNome())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
