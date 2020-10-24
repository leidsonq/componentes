package br.com.o.q.leidson.componentes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.o.q.leidson.componentes.domain.Conjunto;

public class ConjuntoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty (message = "O campo descrição não pode ser vazio!")
	@Length (min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String descricao;
	
	public ConjuntoDTO () {
	}
	
	public ConjuntoDTO (Conjunto obj) {
		id = obj.getId();
		descricao = obj.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
