package br.com.o.q.leidson.componentes.resources;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.o.q.leidson.componentes.domain.Componente;
import br.com.o.q.leidson.componentes.dto.ComponenteDTO;
import br.com.o.q.leidson.componentes.resources.util.URL;
import br.com.o.q.leidson.componentes.services.ComponenteService;

@RestController
@RequestMapping(value = "/componentes")
public class ComponenteResource {

	@Autowired
	ComponenteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Componente> find(@PathVariable Integer id) {
		Componente obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ComponenteDTO objDto) {
		Componente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ComponenteDTO objDto, @PathVariable Integer id) {
		Componente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	/*
	 * @RequestMapping(method = RequestMethod.GET) public
	 * ResponseEntity<List<ComponenteDTO>> findAll() { List<Componente> list =
	 * service.findAll(); List<ComponenteDTO> listDto = list.stream().map(obj -> new
	 * ComponenteDTO(obj)).collect(Collectors.toList()); return
	 * ResponseEntity.ok().body(listDto);
	 * 
	 * }
	 */

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ComponenteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "descricao", defaultValue = "") String descricao,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		List<Integer> ids = URL.decodeIntList(categorias);
		String descricaoDecode = URL.decodeParam(descricao);
		Page<Componente> list = service.search(descricaoDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ComponenteDTO> listDto = list.map(obj -> new ComponenteDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}

}
