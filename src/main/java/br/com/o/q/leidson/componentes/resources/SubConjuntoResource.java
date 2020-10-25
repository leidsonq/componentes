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
import br.com.o.q.leidson.componentes.domain.SubConjunto;
import br.com.o.q.leidson.componentes.dto.SubConjuntoDTO;
import br.com.o.q.leidson.componentes.services.SubConjuntoService;

@RestController
@RequestMapping(value = "/subconjuntos")
public class SubConjuntoResource {

	@Autowired
	SubConjuntoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		SubConjunto obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody SubConjuntoDTO objDto) {
		SubConjunto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody SubConjuntoDTO objDto, @PathVariable Integer id) {
		SubConjunto obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<SubConjuntoDTO>> findAll() {
		List<SubConjunto> list = service.findAll();
		List<SubConjuntoDTO> listDto = list.stream().map(obj -> new SubConjuntoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}

}
