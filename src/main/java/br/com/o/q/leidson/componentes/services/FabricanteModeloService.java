package br.com.o.q.leidson.componentes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.FabricanteModelo;
import br.com.o.q.leidson.componentes.repositories.FabricanteModeloRepository;

@Service
public class FabricanteModeloService {
	
	@Autowired
	private FabricanteModeloRepository repo;

	public FabricanteModelo find(Integer id) {
		Optional<FabricanteModelo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.o.q.leidson.componentes.services.exceptions.ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + FabricanteModelo.class.getName()));
		}
	
	
}
