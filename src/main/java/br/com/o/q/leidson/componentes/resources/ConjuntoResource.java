package br.com.o.q.leidson.componentes.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.dto.ConjuntoDTO;
import br.com.o.q.leidson.componentes.services.ConjuntoService;

@RestController
@RequestMapping(value = "/conjuntos")
public class ConjuntoResource {

	@Autowired
	ConjuntoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Conjunto obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ConjuntoDTO objDto) {
		Conjunto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ConjuntoDTO objDto, @PathVariable Integer id) {
		Conjunto obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ConjuntoDTO>> findAll() {
		List<Conjunto> list = service.findAll();
		List<ConjuntoDTO> listDto = list.stream().map(obj -> new ConjuntoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

}
