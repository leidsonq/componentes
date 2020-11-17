package br.com.o.q.leidson.componentes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.o.q.leidson.componentes.domain.Usuario;
import br.com.o.q.leidson.componentes.dto.UsuarioDTO;
import br.com.o.q.leidson.componentes.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;

	public Usuario find(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new br.com.o.q.leidson.componentes.services.exceptions.ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
		}
	
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData (newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete (Integer id) {
		find(id);
		repo.deleteById(id);

	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Usuario fromDTO (UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), null);
	}
	
	private void updateData (Usuario newObj, Usuario obj) {
		newObj.setNome (obj.getNome());
	}

}
