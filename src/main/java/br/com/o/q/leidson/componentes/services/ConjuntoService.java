package br.com.o.q.leidson.componentes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.Conjunto;
import br.com.o.q.leidson.componentes.dto.ConjuntoDTO;
import br.com.o.q.leidson.componentes.repositories.ConjuntoRepository;
import br.com.o.q.leidson.componentes.services.exceptions.DataIntegrityException;

@Service
public class ConjuntoService {
	
	@Autowired
	private ConjuntoRepository repo;

	public Conjunto find(Integer id) {
		Optional<Conjunto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.o.q.leidson.componentes.services.exceptions.ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Conjunto.class.getName()));
		}
	
	public Conjunto insert(Conjunto obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Conjunto update(Conjunto obj) {
		Conjunto newObj = find(obj.getId());
		updateData (newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete (Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um conjunto que possui componentes");
			
		}
	}
	
	public List<Conjunto> findAll(){
		return repo.findAll();
	}
	
	public Conjunto fromDTO (ConjuntoDTO objDto) {
		return new Conjunto(objDto.getId(), objDto.getDescricao(), null);
	}
	
	private void updateData (Conjunto newObj, Conjunto obj) {
		newObj.setDescricao (obj.getDescricao());
	}

}
