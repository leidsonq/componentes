package br.com.o.q.leidson.componentes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.services.ConjuntoService;

@RestController
@RequestMapping (value="/conjuntos")
public class ConjuntoResource {
	
	@Autowired
	ConjuntoService service;
	
	@RequestMapping (value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find (@PathVariable Integer id) {
		Conjunto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}

}
