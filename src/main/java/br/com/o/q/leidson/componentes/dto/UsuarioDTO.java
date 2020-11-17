package br.com.o.q.leidson.componentes.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.o.q.leidson.componentes.domain.Usuario;
import br.com.o.q.leidson.componentes.services.validation.UsuarioUpdate;

@UsuarioUpdate
public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty (message = "Prenchimento obrigatório!")
	@Length (min = 5, max = 120, message = "O tamanho de ser entre 4 e 120 caracteres")
	private String nome;
	@NotEmpty (message = "Preenchimento obrigatório")
	@Email (message = "Email inválido")
	private String email;
	
	public UsuarioDTO () {
		
	}
	
	public UsuarioDTO (Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
