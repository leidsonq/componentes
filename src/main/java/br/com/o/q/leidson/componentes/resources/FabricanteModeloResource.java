package br.com.o.q.leidson.componentes.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.o.q.leidson.componentes.domain.FabricanteModelo;
import br.com.o.q.leidson.componentes.dto.FabricanteModeloDTO;
import br.com.o.q.leidson.componentes.services.FabricanteModeloService;

@RestController
@RequestMapping (value="/modelos")
public class FabricanteModeloResource {
	
	@Autowired
	FabricanteModeloService service;
	
	@RequestMapping (value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<?> find (@PathVariable String id) {
		Integer idd = Integer.parseInt(id);
		FabricanteModelo obj = service.find(idd);
		return ResponseEntity.ok().body(obj);
		
	}
	
	//Envia a decomposicao do modelo indicado para o email recebido como parametro
	@RequestMapping (value="/decomposicao", method= RequestMethod.GET)
	public ResponseEntity<?> find (@RequestParam (value="id") String id, @RequestParam(value="email") String email) {
		Integer idd = Integer.parseInt(id);
		service.EnviarDecomposicao(idd, email);
		return ResponseEntity.ok().body("Enviado com sucesso!");
		
	}
	
	//Envia a lista de pecas estrategicas do modelo indicado para o email recebido como parametro
	@RequestMapping (value="/estrategica", method= RequestMethod.GET)
	public ResponseEntity<?> findEstrategicas (@RequestParam (value="id") String id, @RequestParam(value="email") String email) {
		Integer idd = Integer.parseInt(id);
		service.EnviarEstrategicas(idd, email);
		return ResponseEntity.ok().body("Enviado com sucesso!");
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FabricanteModeloDTO objDto) {
		FabricanteModelo obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody FabricanteModeloDTO objDto, @PathVariable Integer id) {
		FabricanteModelo obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();

	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FabricanteModeloDTO>> findAll() {
		List<FabricanteModelo> list = service.findAll();
		List<FabricanteModeloDTO> listDto = list.stream().map(obj -> new FabricanteModeloDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}*/

}
