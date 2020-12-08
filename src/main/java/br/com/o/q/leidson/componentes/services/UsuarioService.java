package br.com.o.q.leidson.componentes.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.o.q.leidson.componentes.domain.Usuario;
import br.com.o.q.leidson.componentes.domain.enums.Perfil;
import br.com.o.q.leidson.componentes.dto.UsuarioDTO;
import br.com.o.q.leidson.componentes.repositories.UsuarioRepository;
import br.com.o.q.leidson.componentes.security.UserSS;
import br.com.o.q.leidson.componentes.services.exceptions.AuthorizationException;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;

	public Usuario find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
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
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);

	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario fromDTO(UsuarioDTO objDto) {
		return new Usuario(objDto.getId(), objDto.getNome(), objDto.getEmail(), null);
	}

	public Usuario fromDTO(br.com.o.q.leidson.componentes.dto.UsuarioNewDTO objDto) {
		Usuario usu = new Usuario(null, objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));
		usu.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			usu.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			usu.getTelefones().add(objDto.getTelefone3());
		}

		return usu;
	}

	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setNome(obj.getNome());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		URI uri = s3Service.uploadFile(multipartFile);

		Usuario use = find(user.getId());
		use.setImageUrl(uri.toString());
		repo.save(use);

		return uri;
	}

}
