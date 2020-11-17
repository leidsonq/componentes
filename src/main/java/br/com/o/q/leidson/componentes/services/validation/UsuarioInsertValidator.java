package br.com.o.q.leidson.componentes.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.o.q.leidson.componentes.domain.Usuario;
import br.com.o.q.leidson.componentes.dto.UsuarioNewDTO;
import br.com.o.q.leidson.componentes.repositories.UsuarioRepository;
import br.com.o.q.leidson.componentes.resources.exceptions.FieldMessageNome;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioNewDTO> {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}

	@Override
	public boolean isValid(UsuarioNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessageNome> list = new ArrayList<>();
		
		Usuario aux = usuarioRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
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
