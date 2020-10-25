package br.com.o.q.leidson.componentes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import br.com.o.q.leidson.componentes.domain.Categoria;
import br.com.o.q.leidson.componentes.domain.Componente;
import br.com.o.q.leidson.componentes.dto.ComponenteDTO;
import br.com.o.q.leidson.componentes.repositories.CategoriaRepository;
import br.com.o.q.leidson.componentes.repositories.ComponenteRepository;
import br.com.o.q.leidson.componentes.services.exceptions.DataIntegrityException;

@Service
public class ComponenteService {

	@Autowired
	private ComponenteRepository repo;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	public Componente find(Integer id) {
		Optional<Componente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.o.q.leidson.componentes.services.exceptions.ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Componente.class.getName()));
	}

	public Componente insert(Componente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Componente update(Componente obj) {
		Componente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um componente que esteja associado");

		}
	}

	public List<Componente> findAll() {
		return repo.findAll();
	}

	public Componente fromDTO(ComponenteDTO objDto) {
		return new Componente(objDto.getId(), objDto.getDescricao(), null, null, null, null, null, null);
	}

	private void updateData(Componente newObj, Componente obj) {
		newObj.setDescricao(obj.getDescricao());
	}
	
	public Page<Componente> search(String descricao, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search (descricao, categorias, pageRequest);
	}

}
