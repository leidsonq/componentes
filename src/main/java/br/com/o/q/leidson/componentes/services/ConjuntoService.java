package br.com.o.q.leidson.componentes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.repositories.ConjuntoRepository;

@Service
public class ConjuntoService {
	
	@Autowired
	private ConjuntoRepository repo;

	public Conjunto find(Integer id) {
		Optional<Conjunto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.o.q.leidson.componentes.services.exceptions.ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Conjunto.class.getName()));
		}
	
	
}
