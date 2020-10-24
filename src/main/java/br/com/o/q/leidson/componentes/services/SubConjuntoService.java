package br.com.o.q.leidson.componentes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.SubConjunto;
import br.com.o.q.leidson.componentes.dto.SubConjuntoDTO;
import br.com.o.q.leidson.componentes.repositories.SubConjuntoRepository;
import br.com.o.q.leidson.componentes.services.exceptions.DataIntegrityException;

@Service
public class SubConjuntoService {
	
	@Autowired
	private SubConjuntoRepository repo;

	public SubConjunto find(Integer id) {
		Optional<SubConjunto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.o.q.leidson.componentes.services.exceptions.ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + SubConjunto.class.getName()));
		}
	
	public SubConjunto insert(SubConjunto obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public SubConjunto update(SubConjunto obj) {
		SubConjunto newObj = find(obj.getId());
		updateData (newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete (Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um subconjunto que possui componentes");
			
		}
	}
	
	public List<SubConjunto> findAll(){
		return repo.findAll();
	}
	
	public SubConjunto fromDTO (SubConjuntoDTO objDto) {
		return new SubConjunto(objDto.getId(), objDto.getDescricao(), null);
	}
	
	private void updateData (SubConjunto newObj, SubConjunto obj) {
		newObj.setDescricao (obj.getDescricao());
	}

}
